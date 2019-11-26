package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.MenuDao;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 19:21
 */
@Service(interfaceClass = RoleService.class)
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    //分页
    public PageResult queryRoleByPage(Integer currentPage, Integer pageSize, String queryString) {
        //分页助手设置当前页，每页显示条数
        PageHelper.startPage(currentPage, pageSize);
        Page<Role> page = roleDao.selectByCondition(queryString);
        //封装返回的结果对象
        return new PageResult(page.getTotal(), page.getResult());
    }

    //新增角色
    public void insertRole(Role role, Integer[] permissionIds, Integer[] menuIds) {
        roleDao.insertRole(role);
        if (permissionIds != null && permissionIds.length > 0) {
            //新增权限
            for (Integer permissionId : permissionIds) {
                roleDao.insertPermissionByRoleId(role.getId(), permissionId);
            }
        }
        if (menuIds != null && menuIds.length > 0) {
            for (Integer menuId : menuIds) {
                roleDao.insertMenuByRoleId(role.getId(), menuId);
            }
        }
    }

    //根据id查询角色
    public Role queryRoleById(Integer id) {
        return roleDao.queryRoleById(id);
    }

    //更新角色
    public void updateRole(Role role, Integer[] permissionIds, Integer[] menuIds) {
        roleDao.updateRole(role);
        //获取角色id
        Integer roleId = role.getId();
        //删除角色之前拥有的权限
        roleDao.deletePermissionIdsByRoleId(roleId);
        //删除旧的菜单
        roleDao.deleteMenuIdsByRoleId(roleId);
        if (permissionIds != null && permissionIds.length > 0) {
            //新增权限
            for (Integer permissionId : permissionIds) {
                roleDao.insertPermissionByRoleId(role.getId(), permissionId);
            }
        }
        if (menuIds != null && menuIds.length > 0) {
            for (Integer menuId : menuIds) {
                roleDao.insertMenuByRoleId(role.getId(), menuId);
            }
        }

    }

    //删除角色
    public void deleteRoleById(Integer id) {
        //首先删除该角色对应的权限
        roleDao.deletePermissionIdsByRoleId(id);
        //然后删除对应的菜单
        roleDao.deleteMenuIdsByRoleId(id);
        //在删除用户对应的角色
        roleDao.deleteUserIdsByRoleId(id);
        //最后删除角色
        roleDao.deleteRoleById(id);

    }

   //查询所有角色
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    //查询用户已有的角色
    public List<Integer> findRoleIdsByUserId(Integer id) {
        return roleDao.findRoleIdsByUserId(id);
    }


}
