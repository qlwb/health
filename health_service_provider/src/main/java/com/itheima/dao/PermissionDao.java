package com.itheima.dao;

import com.itheima.pojo.Permission;

import java.util.Set;

/**
 * @Author: dxw
 * @Date: 2019/11/23 10:08
 */
public interface PermissionDao {
    //根据角色id查询角色权限
    Set<Permission> findByRoleId(Integer roleId);
}
