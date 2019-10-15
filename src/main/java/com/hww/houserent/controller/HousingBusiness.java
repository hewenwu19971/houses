package com.hww.houserent.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.houserent.entity.Housing_InformationEntity;
import com.hww.houserent.service.impl.HousingBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 住房业务逻辑控制器
 */
@Controller
public class HousingBusiness {
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
    @RequestMapping(value = "/getAllInformation", method = RequestMethod.GET)
    public PageInfo getAllInformation(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        PageInfo<Housing_InformationEntity> entityPageInfo = null;
        if (page > 0) {
            PageHelper.startPage(page, 10);
            List<Housing_InformationEntity> housingInformationEntityList = housingBusiness.getHouse();
            entityPageInfo = new PageInfo<>(housingInformationEntityList);
        }
        return entityPageInfo;
    }

    @RequestMapping("/delFangyuan")
    public String delFangYuan(@RequestParam(value = "fyID") int fyID) {
        housingBusiness.delHouse(fyID);
        return "/house_list.html";
    }


    @RequestMapping("/house_edit")
    public String houseEdit(@RequestParam(value = "fyID") int fyID,
                            HttpSession session) {
        session.setAttribute("fyID", fyID);
        return "house_edit.html";
    }

    @ResponseBody
    @RequestMapping("/house_edits")
    public Map house_edits(HttpSession session) {
       Map<String, Object> map = new  HashMap<String, Object>();
        int fyID = (int) session.getAttribute("fyID");
        Housing_InformationEntity editHouse = housingBusiness.editHouse(fyID);
        map.put("house",editHouse);
        return map;
    }
    @GetMapping("/checkFyFhIsExists")
    public String checkFyFhIsExists(@RequestParam(value = "fyID") int fyID,
                                    @RequestParam(value = "fyXqCode") String fyXqCode){
        System.out.println(fyID+""+fyXqCode);
         return "";
    }
}
