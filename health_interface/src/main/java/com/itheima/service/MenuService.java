package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Menu;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 11:43
 */
public interface MenuService {
    //查出该角色对应的菜单列表
    List<Menu> queryMenuByUsername(String username);

    //分页
    PageResult queryMenuByPage(Integer currentPage, Integer pageSize, String queryString);
    //根据id查询菜单信息
    Menu queryMenuById(Integer id);

    //新增菜单
    void insertMenu(Menu menu);

    //编辑菜单
    void updateMenu(Menu menu);

    //根据id删除菜单
    void deleteMenuById(Integer id);
}
