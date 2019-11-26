package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.User;

import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/23 10:01
 */
public interface UserDao {
    //根据用户名查询用户信息
    User findByUsername(String username);

    //分页
    Page<User> selectByCondition(String queryString);

    //新增用户
    void add(User user);

    //给用户赋予角色
    void setUserAndRole(Map<String, Integer> map);

    //查询单个用户
    User findById(Integer id);

    //更新用户
    void update(User user);

    //删除用户的角色
    void deleteRoleIdsByUserId(Integer id);

    //删除用户
    void delete(Integer id);
}
