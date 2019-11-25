package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MenuDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @Author: dxw
 * @Date: 2019/11/25 11:43
 */
@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {

   @Autowired
    private MenuDao menuDao;
   @Autowired
   private RoleDao roleDao;
   @Autowired
   private UserDao userDao;

   //查出该角色对应的菜单列表
    public List<Menu> queryMenuByUsername(String username) {
        List<Menu> menuParentList=null;
        User user = userDao.findByUsername(username);
        if (user!=null){
            Set<Role> roles = roleDao.findByUserId(user.getId());
            if (roles!=null){
                for (Role role : roles) {
                    //查出该角色对应的一级菜单并递归查询子菜单
                    menuParentList = menuDao.queryMenuByRoleIdAndLevel(role.getId(),1);
                    if (menuParentList!=null) {
                        for (Menu menuParent : menuParentList) {
                            menuParent.setChildren(menuDao.queryMenuByParentMenuIdAndRoleId(role.getId(),menuParent.getId()));
                        }
                    }
                }
            }
        }
        return menuParentList;
    }
}
