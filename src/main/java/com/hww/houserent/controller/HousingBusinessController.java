package com.hww.houserent.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.houserent.entity.Housing_InformationEntity;
import com.hww.houserent.service.impl.HousingBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @RequestMapping(value = "/getAllInformation", method = RequestMethod.GET)
    public PageInfo getAllInformation(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {

        PageInfo<Housing_InformationEntity> entityPageInfo = null;
        System.out.println(page);
        if (page >= 1) {
            PageHelper.startPage(page, 10);
            List<Housing_InformationEntity> house = housingBusiness.getHouse();
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
}
