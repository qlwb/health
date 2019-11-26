package com.itheima.service;

/**
 * @Author: dxw
 * @Date: 2019/11/23 9:52
 */

import com.itheima.entity.PageResult;
import com.itheima.pojo.User;

public interface UserService {
    //根据用户名查询用户信息
     User findByUsername(String username);

     //分页查询
    PageResult queryMenuByPage(Integer currentPage, Integer pageSize, String queryString);

    //新增用户
    void add(User user, Integer[] roleIds);

    //查询单个用户
    User findById(Integer id);

    //编辑用户
    void update(User user, Integer[] roles);

    //删除用户
    void delete(Integer id);
}
