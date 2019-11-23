package com.itheima.dao;

import com.itheima.pojo.User;

/**
 * @Author: dxw
 * @Date: 2019/11/23 10:01
 */
public interface UserDao {
    //根据用户名查询用户信息
    User findByUsername(String username);
}
