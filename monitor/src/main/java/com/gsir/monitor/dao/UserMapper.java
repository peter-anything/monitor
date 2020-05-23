package com.gsir.monitor.dao;

import org.apache.ibatis.annotations.Param;

import com.gsir.monitor.pojo.User;

public interface UserMapper {
    public User getUserById(@Param("id")Integer id);
}
