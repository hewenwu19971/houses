package com.hww.houserent.service;

import com.hww.houserent.entity.Housing_InformationEntity;
import com.hww.houserent.mapper.HousingBusinessMapper;

import java.util.List;
import java.util.Map;

public interface HousingBusinessService {
    List<Housing_InformationEntity> getHouse();
    int delHouse(int id);
    Housing_InformationEntity editHouse(int id);
    Housing_InformationEntity inspect(Housing_InformationEntity housing_informationEntity);
    int updateInformation(Housing_InformationEntity housing_informationEntity);
    int addInformation(Housing_InformationEntity housing_informationEntity);
    int batchDeletion(int[] id);
    List<String> searchArea();
    List<String> getNumber(String fyXqCode);
    List<String> getApartment(Map map);
    List<String> getState(Map map);
}
