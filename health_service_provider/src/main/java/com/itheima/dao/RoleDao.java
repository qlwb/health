package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Author: dxw
 * @Date: 2019/11/23 10:08
 */
public interface RoleDao {
    //根据用户id查询用户角色
    Set<Role> findByUserId(Integer userId);

    //分页
    Page<Role> selectByCondition(String queryString);

    //新增角色
    void insertRole(Role role);

    //给角色赋予权限
    void insertPermissionByRoleId(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    //给角色赋予菜单项
    void insertMenuByRoleId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    //根据id查询角色
    Role queryRoleById(Integer id);

    //删除角色拥有的权限
    void deletePermissionIdsByRoleId(Integer roleId);

    //删除角色拥有的菜单
    void deleteMenuIdsByRoleId(Integer roleId);

    //更新角色
    void updateRole(Role role);

    //删除用户对应的角色
    void deleteUserIdsByRoleId(Integer id);

    //删除角色
    void deleteRoleById(Integer id);

    //查询所有
    List<Role> findAll();

    //查询单个用户拥有的角色
    List<Integer> findRoleIdsByUserId(Integer id);
}
