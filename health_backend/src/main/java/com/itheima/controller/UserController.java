package com.itheima.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dxw
 * @Date: 2019/11/23 10:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    //获取当前登录用户的用户名
    @RequestMapping("/getUsername")
    public Result getUsername()throws Exception{
        try{
            //当Spring security完成认证后，会将当前用户信息保存到框架提供的上下文对象中
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(user!=null){
                return new Result(true,
                        MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
            }
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }catch (Exception e){
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }

}
