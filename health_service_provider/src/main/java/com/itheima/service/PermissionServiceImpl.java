package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.PermissionDao;
import com.itheima.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 19:36
 */
@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    //查询所有权限
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    //查询角色拥有的权限id
    public List<Integer> queryPermissionIdsByRoleId(Integer roleId) {
        return  permissionDao.queryPermissionIdsByRoleId(roleId);
    }
}
