package com.hww.houserent.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.houserent.entity.Housing_InformationEntity;
import com.hww.houserent.service.impl.HousingBusinessImpl;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 住房业务逻辑控制器
 */
@Controller
public class HousingBusinessController {
    @Autowired
    private HousingBusinessImpl housingBusiness;

    @RequestMapping("/house_list")
    public String house_list() {
        return "/house_list.html";
    }

    /**
     * 分页插件pagehelper
     *
     * @param page 当前页
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllInformation")
    public PageInfo getAllInformation(@RequestParam(value = "page", required = false, defaultValue = "1") int page
            , @RequestParam(value ="fyDhCode" ,required = false) String fyDhCode
            , @RequestParam(value ="fyHxCode" ,required = false) String fyHxCode
            , @RequestParam(value ="fyXqCode" ,required = false) String fyXqCode
            , @RequestParam(value ="fyStatus" ,required = false) String fyStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("fyDhCode",fyDhCode);
        map.put("fyHxCode",fyHxCode);
        map.put("fyXqCode",fyXqCode);
        map.put("fyStatus",fyStatus);

        System.out.println("map>>>>>>" + map.toString());
        PageInfo<Housing_InformationEntity> entityPageInfo = null;
        System.out.println(page);
        if (page >= 1) {
            PageHelper.startPage(page, 10);
            List<Housing_InformationEntity> house = housingBusiness.getHouse(map);
            entityPageInfo = new PageInfo<>(house);
        }
        return entityPageInfo;
    }

    /**
     * 删除
     *
     * @param fyID
     * @return
     */
    @RequestMapping("/delFangyuan")
    public String delFangYuan(@RequestParam(value = "fyID") int fyID) {
        housingBusiness.delHouse(fyID);
        return "/house_list.html";
    }

    /**
     * 编辑
     *
     * @param fyID
     * @param session
     * @return
     */
    @RequestMapping("/house_edit")
    public String houseEdit(@RequestParam(value = "fyID") int fyID,
                            HttpSession session) {
        if (fyID > 0) {
            session.setAttribute("fyID", fyID);
        }
        return "house_edit.html";
    }

    @ResponseBody
    @RequestMapping("/house_edits")
    public Map house_edits(HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (session.getAttribute("fyID") != null) {
            int fyID = (int) session.getAttribute("fyID");
            Housing_InformationEntity editHouse = housingBusiness.editHouse(fyID);
            session.removeAttribute("fyID");
            map.put("house", editHouse);
            return map;
        }
        return map;
    }

    @ResponseBody
    @PostMapping(value = "/checkFyFhIsExists")
    public String checkFyFhIsExists(@RequestBody Housing_InformationEntity housing_informationEntity) {
        Housing_InformationEntity entity = housingBusiness.inspect(housing_informationEntity);
        if (entity != null) {
            return "1";
        }
        return "2";
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateFangyuan", method = RequestMethod.POST)
    public String saveOrUpdateFangyuan(@RequestBody Housing_InformationEntity housing_informationEntity,
                                       HttpSession session) {
        int i = housingBusiness.updateInformation(housing_informationEntity);
        if (i > 0) {
            return "1";
        }
        return "0";
    }

    @ResponseBody
    @RequestMapping(value = "/newlyAddedFangyuan", method = RequestMethod.POST)
    public String newlyAddedFangyuan(@RequestBody Housing_InformationEntity housing_informationEntity) {
        int i = housingBusiness.addInformation(housing_informationEntity);
        if (i > 0) {
            return "1";
        }
        return "0";
    }

    @RequestMapping("/batchDelFangyuan")
    public String batchDelFangyuan(@RequestParam(value = "IDCheck") int[] IDCheck) {
        int i = housingBusiness.batchDeletion(IDCheck);
        return "/house_list.html";
    }

    @ResponseBody
    @RequestMapping("/searchArea")
    public List<String> searchArea() {
        List<String> list = housingBusiness.searchArea();
        System.out.println(">>>>>>>>>>>>>>" + list.toString());
        return list;
    }

    @ResponseBody
    @RequestMapping("/getFyDhListByFyXqCode")
    public List<String> getFyDhListByFyXqCode(@RequestParam(value = "fyXqCode") String fyXqCode) {
        List<String> list = housingBusiness.getNumber(fyXqCode);
        return list;
    }

    @ResponseBody
    @RequestMapping("/getFyDhListByFyDhCode")
    public List<String> getFyDhListByFyDhCode(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        List<String> list = housingBusiness.getApartment(map);
        return list;
    }

    @ResponseBody
    @RequestMapping("/getFyDhListByFyHxCode")
    public List<String> getFyDhListByFyHxCode(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        List<String> list = housingBusiness.getState(map);
        return list;
    }

    @ResponseBody
    @RequestMapping("/search")
    public PageInfo search(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        PageInfo<Housing_InformationEntity> entityPageInfo = null;
       /* if (page > 0) {
            PageHelper.startPage(page, 10);
            List<Housing_InformationEntity> housingInformationEntityList = housingBusiness.getHouse();
            entityPageInfo = new PageInfo<>(housingInformationEntityList);
        }*/
        return entityPageInfo;
    }


    @RequestMapping("/exportHouse")
    public void excel(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("综合满意度评分结果");

        String fileName = "房屋信息" + ".xls";// 设置要导出的文件的名字
        String[] headers = {"位置", "小区", "楼栋号", "楼层", "房间号", "房源面积", "计租面积", "户型", "建筑结构"};
        HashMap<Object, Object> map = new HashMap<>();
        List<Housing_InformationEntity> classmateList = housingBusiness.getHouse(map);
        /*List<Teacher> classmateList = new ArrayList<Teacher>();
        Teacher t1 = new Teacher("研发中心", "陈望都", "王斌（VP)", 5, 5, 11, 2, 3, 5, 20);
        Teacher t2 = new Teacher("视频事业部", "徐颖", "赵颖(VP）", 5, 5, 13, 1, 6, 7, 21);
        Teacher t3 = new Teacher("研发部门", "林勇", "陈望都", 5, 5, 14, 11, 3, 5, 6);

        classmateList.add(t1);
        classmateList.add(t2);
        classmateList.add(t3);
*/
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 9);
        sheet.addMergedRegion(region);
        HSSFRow rowTitle = sheet.createRow(0);
        Cell oneCell = rowTitle.createCell(0);
        oneCell.setCellValue("房屋信息表");// 设置标题内容

        // 合并的单元格样式
        HSSFCellStyle boderStyle = workbook.createCellStyle();
        boderStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        boderStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        oneCell.setCellStyle(boderStyle);

        HSSFRow row = sheet.createRow(1);
        // 在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        int rowNum = 2;
        // 在表中存放查询到的数据放入对应的列
        for (Housing_InformationEntity teacher : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(teacher.getPosition());
            row1.createCell(1).setCellValue(teacher.getCommunity());
            row1.createCell(2).setCellValue(teacher.getBuilding_number());
            row1.createCell(3).setCellValue(teacher.getFloor());
            row1.createCell(4).setCellValue(teacher.getRoom_number());
            row1.createCell(5).setCellValue(teacher.getHousing_area());
            row1.createCell(6).setCellValue(teacher.getRental_area());
            row1.createCell(7).setCellValue(teacher.getApartment());
            row1.createCell(8).setCellValue(teacher.getBuilding_structure());
            row1.createCell(9).setCellValue(teacher.getNature_of_lease());
            row1.createCell(10).setCellValue(teacher.getState());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


}
