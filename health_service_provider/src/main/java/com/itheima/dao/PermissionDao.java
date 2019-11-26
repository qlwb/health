package com.itheima.dao;

import com.github.pagehelper.Page;
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

    //分页
    Page<Permission> selectByCondition(String queryString);

    //添加
    void add(Permission permission);

    //根据id查找权限
    Permission findById(Integer id);

    //修改
    void update(Permission permission);

    //根据id删除
    void deleteById(Integer id);

    //根据权限id删除角色id
    void deleteRoleIdByPermissionId(Integer id);
}
