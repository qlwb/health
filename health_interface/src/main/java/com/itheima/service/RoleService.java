package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Role;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 19:20
 */
public interface RoleService {
    //分页
    PageResult queryRoleByPage(Integer currentPage, Integer pageSize, String queryString);

    //新增角色
    void insertRole(Role role, Integer[] permissionIds, Integer[] menuIds);

    //根据id查询角色
    Role queryRoleById(Integer id);

    //更新角色
    void updateRole(Role role, Integer[] permissionIds, Integer[] menuIds);

    //删除角色
    void deleteRoleById(Integer id);

    //查询所有角色
    List<Role> findAll();

    //查询用户已有的角色
    List<Integer> findRoleIdsByUserId(Integer id);
}
