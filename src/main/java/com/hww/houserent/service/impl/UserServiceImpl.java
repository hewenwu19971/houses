package com.hww.houserent.service.impl;

import com.hww.houserent.entity.UserEntity;
import com.hww.houserent.mapper.UserMapper;
import com.hww.houserent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public UserEntity login(String username) {
        return userMapper.byUserName(username);
    }

    @Override
    public UserEntity getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public Set<String> getPermissions(String username) {
        return userMapper.getPermissions(username);
    }

    @Override
    public Set<String> getRoles(String username) {
        return userMapper.getRoles(username);
    }

    @Override
    public Integer setUser(UserEntity userEntity) {
        return userMapper.setUser(userEntity);
    }
}
