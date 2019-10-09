package com.hww.houserent.service.impl;

import com.hww.houserent.mapper.UserMapper;
import com.hww.houserent.entity.UserEntity;
import com.hww.houserent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public UserEntity login(String username) {
        return userMapper.byUserName(username);
    }
}
