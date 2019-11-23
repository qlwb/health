package com.itheima.service;

/**
 * @Author: dxw
 * @Date: 2019/11/23 9:52
 */

import com.itheima.pojo.User;

public interface UserService {
    //根据用户名查询用户信息
     User findByUsername(String username);
}
