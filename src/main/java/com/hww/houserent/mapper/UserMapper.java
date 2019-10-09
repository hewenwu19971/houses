package com.hww.houserent.mapper;

import com.hww.houserent.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public UserEntity byUserName (String username);

}
