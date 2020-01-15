package com.hww.houserent.mapper;

import com.hww.houserent.entity.Housing_InformationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface HousingBusinessMapper {
    /** 查询所有 **/
    List<Housing_InformationEntity> getPropertyInformation(Map map);
    /** 单个删除 **/
    int SingleDeletion(int id);
    /** 编辑 **/
    Housing_InformationEntity getHousingInformation(int id);
    /** 重复检查 **/
    Housing_InformationEntity checkDuplication (Housing_InformationEntity housing_informationEntity);
    /** 更新操作**/
    int updataFanYuan (Housing_InformationEntity housing_informationEntity);
    /** 添加操作 **/
    int insertFanYuan(Housing_InformationEntity housing_informationEntity);
    /** 批量删除 **/
    int deleteFanYuan(int[] id);
    /** 去重**/
    List<String> SelectCommunity();
    /** 获取栋号**/
    List<String> selectNumber(String fyxq);
    /** 获取户型**/
    List<String> selectApartment(Map map);
    /** 获取状态**/
    List<String> selectState(Map map);
}
