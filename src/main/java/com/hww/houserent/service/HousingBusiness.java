package com.hww.houserent.service;

import com.hww.houserent.entity.Housing_InformationEntity;
import com.hww.houserent.mapper.HousingBusinessMapper;

import java.util.List;
import java.util.Map;

public interface HousingBusiness {
    List<Housing_InformationEntity> getHouse();
    int delHouse(int id);
    Housing_InformationEntity editHouse(int id);
}
