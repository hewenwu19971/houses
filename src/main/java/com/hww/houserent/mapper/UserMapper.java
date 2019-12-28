package com.hww.houserent.mapper;

import com.hww.houserent.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface UserMapper {

    public UserEntity byUserName (String username);

    UserEntity getUserByName(String username);

    Set<String> getPermissions (String username);

    Set<String> getRoles(String username);
}
