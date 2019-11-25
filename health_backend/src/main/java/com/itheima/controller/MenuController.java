package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Menu;
import com.itheima.pojo.MenuChild;
import com.itheima.service.MenuService;
import org.springframework.web.bind.annotation.PathVariable;
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
    private MenuService menuServiceImpl;

    //查出该角色对应的菜单列表
    @RequestMapping("getMenuByUsername/{username}")
    public Result getMenuByUsername(@PathVariable String username){//接收请求路径中占位符的值
        try{
            List<Menu> menus = menuServiceImpl.queryMenuByUsername(username);
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
            return new Result(true, MessageConstant.GET_MENU_SUCCESS,menus);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MENU_FAIL);
        }
    }
}
