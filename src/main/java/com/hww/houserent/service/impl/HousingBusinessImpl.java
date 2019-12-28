package com.hww.houserent.service.impl;

import com.hww.houserent.entity.Housing_InformationEntity;
import com.hww.houserent.mapper.HousingBusinessMapper;
import com.hww.houserent.service.HousingBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HousingBusinessImpl implements HousingBusinessService {
    @Autowired
    private HousingBusinessMapper housingBusinessMapper;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public List<Housing_InformationEntity> getHouse() {

        /*RedisSerializer redisSerializer = new Jackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);*/
        List<Housing_InformationEntity> housing_informationEntities = (List<Housing_InformationEntity>) redisTemplate.opsForValue().get("getHouse");
        if (null == housing_informationEntities){
            housing_informationEntities =  housingBusinessMapper.getPropertyInformation();
            redisTemplate.opsForValue().set("getHouse",housing_informationEntities);
        }
        return housing_informationEntities;
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

    @Override
    public int addInformation(Housing_InformationEntity housing_informationEntity) {
        return housingBusinessMapper.insertFanYuan(housing_informationEntity);
    }

    @Override
    public int batchDeletion(int[] id) {
        return housingBusinessMapper.deleteFanYuan(id);
    }

    @Override
    public List<String> searchArea() {
        return housingBusinessMapper.SelectCommunity();
    }

    @Override
    public List<String> getNumber(String fyXqCode) {
        return housingBusinessMapper.selectNumber(fyXqCode);
    }

    @Override
    public List<String> getApartment(Map map) {
        return housingBusinessMapper.selectApartment(map);
    }

    @Override
    public List<String> getState(Map map) {
        return housingBusinessMapper.selectState(map);
    }
}

