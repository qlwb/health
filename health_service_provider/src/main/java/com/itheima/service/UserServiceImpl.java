package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: dxw
 * @Date: 2019/11/23 9:59
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    //根据用户名查询用户信息
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }
        Integer userId = user.getId();
        //根据用户id查询用户角色
        Set<Role> roles = roleDao.findByUserId(userId);
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                Integer roleId = role.getId();
                //根据角色id查询角色权限
                Set<Permission> permissions = permissionDao.findByRoleId(roleId);
                if (permissions != null && permissions.size() > 0) {
                    role.setPermissions(permissions);
                }
            }
            user.setRoles(roles);
        }
        return user;
    }

    //分页查询
    public PageResult queryMenuByPage(Integer currentPage, Integer pageSize, String queryString) {
        //分页助手设置当前页，每页显示条数
        PageHelper.startPage(currentPage, pageSize);
        Page<User> page = userDao.selectByCondition(queryString);
        //封装返回的结果对象
        return new PageResult(page.getTotal(), page.getResult());
    }

    //新增用户
    public void add(User user, Integer[] roleIds) {
        userDao.add(user);
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("user_id", user.getId());
                map.put("role_id", roleId);
                userDao.setUserAndRole(map);
            }
        }
    }

    //查询单个用户
    public User findById(Integer id) {
        return userDao.findById(id);
    }

   //编辑用户
    public void update(User user, Integer[] roles) {
        userDao.update(user);
        userDao.deleteRoleIdsByUserId(user.getId());
        if(roles!=null&&roles.length>0){
            for (Integer roleId : roles) {
                Map<String, Integer> map = new HashMap<>();
                map.put("user_id", user.getId());
                map.put("role_id", roleId);
                userDao.setUserAndRole(map);
            }
        }
    }

    //删除用户
    public void delete(Integer id) {
        userDao.deleteRoleIdsByUserId(id);
        userDao.delete(id);

    }

}
