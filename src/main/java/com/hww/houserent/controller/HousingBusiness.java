package com.hww.houserent.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hww.houserent.entity.Housing_InformationEntity;
import com.hww.houserent.service.impl.HousingBusinessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
     * @param page 当前页
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllInformation", method = RequestMethod.GET)
    public PageInfo getAllInformation(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
        PageInfo<Housing_InformationEntity> entityPageInfo = null;
        if (page > 0) {
            PageHelper.startPage(page, 10);
            List<Housing_InformationEntity> housingInformationEntityList = housingBusiness.getHouse();
             entityPageInfo = new PageInfo<>(housingInformationEntityList);
        }
        return entityPageInfo;
    }

    @RequestMapping("/delFangyuan")
    public String delFangyuan(@RequestParam(value = "fyID") int fyID){
        housingBusiness.delHouse(fyID);
        return "/house_list.html";
    }
}
