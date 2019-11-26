package com.itheima.dao;

import com.itheima.pojo.Permission;

import java.util.List;
import java.util.Set;

/**
 * @Author: dxw
 * @Date: 2019/11/23 10:08
 */
public interface PermissionDao {
    //根据角色id查询角色权限
    Set<Permission> findByRoleId(Integer roleId);

    //查询所有权限
    List<Permission> findAll();

    //查询角色拥有的权限id
    List<Integer> queryPermissionIdsByRoleId(Integer roleId);
}
