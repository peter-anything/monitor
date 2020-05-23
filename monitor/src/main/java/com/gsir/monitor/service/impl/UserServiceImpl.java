package com.gsir.monitor.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsir.monitor.dao.UserMapper;
import com.gsir.monitor.pojo.User;
import com.gsir.monitor.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

}
