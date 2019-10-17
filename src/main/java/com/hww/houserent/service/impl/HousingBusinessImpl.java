package com.hww.houserent.service.impl;

import com.hww.houserent.entity.Housing_InformationEntity;
import com.hww.houserent.mapper.HousingBusinessMapper;
import com.hww.houserent.service.HousingBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HousingBusinessImpl implements HousingBusiness {
    @Autowired
    private HousingBusinessMapper housingBusinessMapper;


    @Override
    public List<Housing_InformationEntity> getHouse() {
        return housingBusinessMapper.getPropertyInformation();
    }

    @Override
    public int delHouse(int id) {
        return housingBusinessMapper.SingleDeletion(id);
    }

    @Override
    public Housing_InformationEntity editHouse(int id) {
        return housingBusinessMapper.getHousingInformation(id);
    }

    @Override
    public Housing_InformationEntity inspect(Housing_InformationEntity housing_informationEntity) {
        return housingBusinessMapper.checkDuplication(housing_informationEntity);
    }

    @Override
    public int updateInformation(Housing_InformationEntity housing_informationEntity) {
        return housingBusinessMapper.updataFanYuan(housing_informationEntity);
    }
}

