package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Setmeal;
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

    //分页
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //分页助手设置当前页，每页显示条数
        PageHelper.startPage(currentPage, pageSize);
        Page<Permission> page = permissionDao.selectByCondition(queryString);
        //封装返回的结果对象
        return new PageResult(page.getTotal(), page.getResult());
    }

    //添加
    public void add(Permission permission) {
        permissionDao.add(permission);
    }

    //根据id查找权限
    public Permission findById(Integer id) {
        return permissionDao.findById(id);
    }

    //修改
    public void update(Permission permission) {
        permissionDao.update(permission);
    }

    //根据id删除
    public void deleteById(Integer id) {
        permissionDao.deleteRoleIdByPermissionId(id);
        permissionDao.deleteById(id);
    }
}
