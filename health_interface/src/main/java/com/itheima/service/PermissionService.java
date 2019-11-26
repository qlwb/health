package com.itheima.service;

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
}
