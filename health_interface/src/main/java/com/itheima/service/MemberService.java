package com.itheima.service;

import com.itheima.pojo.Member;

/**
 * @Author: dxw
 * @Date: 2019/11/22 21:18
 */
//会员登录
public interface MemberService {

    //判断当前用户是否为会员
    Member findByTelephone(String telephone);

    //注册会员
    void add(Member member);
}
