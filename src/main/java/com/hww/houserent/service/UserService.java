package com.hww.houserent.service;

import com.hww.houserent.entity.UserEntity;

import java.util.Set;

public interface UserService {
    UserEntity login (String username);

    UserEntity getUserByName(String username);

    Set<String> getPermissions (String username);

    Set<String> getRoles(String username);

    Integer setUser(UserEntity userEntity);
}
