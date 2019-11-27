package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Member;

import java.util.List;

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

    //查询yyyy.MM的会员人数
    List<Integer> findMemberCountByMonth(List<String> list);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}
