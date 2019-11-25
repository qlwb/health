package com.itheima.dao;

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
}
