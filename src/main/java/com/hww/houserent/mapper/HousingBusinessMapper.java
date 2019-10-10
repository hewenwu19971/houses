package com.hww.houserent.mapper;

import com.hww.houserent.entity.Housing_InformationEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface HousingBusinessMapper {
    /** 查询所有 **/
    List<Housing_InformationEntity> getPropertyInformation();
    /** 单个删除 **/
    int SingleDeletion(int id);
    /** 编辑 **/
    Housing_InformationEntity getHousingInformation(int id);
}
