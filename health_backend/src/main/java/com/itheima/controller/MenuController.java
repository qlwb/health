package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.pojo.MenuChild;
import com.itheima.service.MenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 11:39
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Reference
    private MenuService menuService;

    //查出该角色对应的菜单列表
    @RequestMapping("/getMenuByUsername/{username}")
    public Result getMenuByUsername(@PathVariable String username) {//接收请求路径中占位符的值
        try {
            List<Menu> menus = menuService.queryMenuByUsername(username);
           /* List<MenuChild> menuChildList=new ArrayList<>();
            for (Menu menu : menus) {
                MenuChild menuChild=new MenuChild();
                menuChild.setPath(menu.getPath());
                menuChild.setTitle(menu.getName());
                menuChild.setIcon(menu.getIcon());//封装一级目录
                List<Menu> menuList = menu.getChildren();
                if (menuList!=null) {
                    List<MenuChild> childList=new ArrayList<>();
                    for (Menu children:menuList) {
                        MenuChild child = new MenuChild();
                        child.setPath(children.getPath());
                        child.setTitle(children.getName());
                        child.setIcon(children.getIcon());
                        child.setLinkUrl(children.getLinkUrl());//封装二级目录
                        childList.add(child);
                    }
                    menuChild.setChildren(childList);
                }
                menuChildList.add(menuChild);
            }*/
            return new Result(true, MessageConstant.GET_MENU_SUCCESS, menus);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_MENU_FAIL);
        }
    }


    //分页查询所有菜单
    @PreAuthorize("hasAuthority('MENU_QUERY')")
    @RequestMapping("/queryMenuByPage")
    public PageResult queryMenuByPage(@RequestBody QueryPageBean queryPageBean) {
        return menuService.queryMenuByPage(queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(), queryPageBean.getQueryString());

    }

    @RequestMapping("/queryMenuById/{id}")
    public Result queryMenuById(@PathVariable Integer id) {
        try {
            return new Result(true, MessageConstant.QUERY_MENU_SUCCESS, menuService.queryMenuById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_MENU_FAIL);
        }
    }

    //编辑菜单
    @PreAuthorize("hasAuthority('MENU_EDIT')")
    @RequestMapping("/updateMenu")
    public Result updateMenu(@RequestBody Menu menu) {
        try {
            menuService.updateMenu(menu);
            return new Result(true, MessageConstant.EDIT_MENU_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_MENU_FAIL);
        }
    }

    //新增菜单
    @PreAuthorize("hasAuthority('MENU_ADD')")
    @RequestMapping("/addMenu")
    public Result insertMenu(@RequestBody Menu menu) {
        try {
            menuService.insertMenu(menu);
            return new Result(true, MessageConstant.ADD_MENU_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MENU_FAIL);
        }
    }


    @PreAuthorize("hasAuthority('MENU_DELETE')")
    @RequestMapping("/deleteMenuById/{id}")
    public Result deleteMenuById(@PathVariable Integer id) {
        try {
            menuService.deleteMenuById(id);
            return new Result(true, MessageConstant.DELETE_MENU_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_MENU_FAIL);
        }
    }


    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Menu> list = menuService.findAll();
            return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL);
        }
    }

    /**
     * 根据角色id查询中间表中的菜单id集合
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/queryMenuIdsById/{roleId}")
    public Result queryMenuIdsById(@PathVariable Integer roleId) {
        try {
            List<Integer> menuIds = menuService.queryMenuIdsByRoleId(roleId);
            return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS,menuIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL);
        }
    }
}
