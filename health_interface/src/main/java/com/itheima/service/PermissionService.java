package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Permission;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 19:36
 */
public interface PermissionService {
    //查询所有权限
    List<Permission> findAll();

    //查询角色拥有的权限id
    List<Integer> queryPermissionIdsByRoleId(Integer roleId);

    //分页
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    //添加
    void add(Permission permission);

    //查询一个权限
    Permission findById(Integer id);

    //修改
    void update(Permission permission);

    //根据id删除
    void deleteById(Integer id);
}
