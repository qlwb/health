package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dxw
 * @Date: 2019/11/23 10:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    //获取当前登录用户的用户名
    @RequestMapping("/getUsername")
    public Result getUsername()throws Exception{
        try{
            //当Spring security完成认证后，会将当前用户信息保存到框架提供的上下文对象中
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(user!=null){
                return new Result(true,
                        MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
            }
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }catch (Exception e){
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }

    //分页查询所有用户
    @PreAuthorize("hasAuthority('USER_QUERY')")
    @RequestMapping("/findPage")
    public PageResult queryMenuByPage(@RequestBody QueryPageBean queryPageBean) {
        return userService.queryMenuByPage(queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(), queryPageBean.getQueryString());

    }

    @PreAuthorize("hasAuthority('USER_ADD')")
    @RequestMapping("/add")
    public Result addCheckGroup(@RequestBody User user, Integer[] roleIds){
        try {
            String password = user.getPassword();
            String encoderPassword = passwordEncoder.encode(password);//密码加密
            user.setPassword(encoderPassword);
            userService.add(user,roleIds);
        }catch (Exception e){
            return new Result(false, MessageConstant.ADD_USER_FAIL);
        }
        return new Result(true, MessageConstant.ADD_USER_SUCCESS);
    }

    //根据id查询单个套餐信息
    @RequestMapping("/findUserById")
    public Result findById(Integer id) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                return new Result(false, MessageConstant.QUERY_USER_FAIL);
            }
            return new Result(true, MessageConstant.QUERY_USER_SUCCESS, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_USER_FAIL);
        }
    }

    @PreAuthorize("hasAuthority('USER_EDIT')")
    @RequestMapping("/update")
    public Result update(@RequestBody User user, Integer[] roleIds) {
        try {
            String password = user.getPassword();
            String encoderPassword = passwordEncoder.encode(password);//密码加密
            user.setPassword(encoderPassword);
            userService.update(user, roleIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_USER_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_USER_SUCCESS);
    }


    @PreAuthorize("hasAuthority('USER_DELETE')")
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_USER_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_USER_SUCCESS);
    }




}
