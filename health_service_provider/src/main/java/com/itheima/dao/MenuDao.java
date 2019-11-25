package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 11:47
 */
public interface MenuDao {

    /**
     * 根据角色id和父id查找二级菜单
     * @param parentMenuId
     * @return
     */
    List<Menu> queryMenuByParentMenuIdAndRoleId(@Param("roleId") Integer roleId, @Param("parentMenuId") Integer parentMenuId);

    /**
     * 根据角色id获得该角色所对应的一级菜单
     * @return
     */
    List<Menu> queryMenuByRoleIdAndLevel(@Param("roleId") Integer roleId, @Param("level") Integer level);

    //分页
    Page<Menu> selectByCondition(String queryString);

    //根据id查询菜单信息
    Menu queryMenuById(Integer id);

    //新增菜单
    void insertMenu(Menu menu);

    //编辑菜单
    void updateMenu(Menu menu);

    //根据id删除菜单
    void deleteMenuById(Integer id);

    //删除中间表中的数据
    void deleteMenuAndRoleByMenuId(Integer id);
}
