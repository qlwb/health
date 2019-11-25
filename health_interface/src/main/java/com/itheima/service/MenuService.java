package com.itheima.service;

import com.itheima.pojo.Menu;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 11:43
 */
public interface MenuService {
    //查出该角色对应的菜单列表
    List<Menu> queryMenuByUsername(String username);
}
