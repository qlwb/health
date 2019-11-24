package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Member;

import java.util.List;

public interface MemberDao {
     //查询所有会员
     List<Member> findAll();

     //根据条件查询
     Page<Member> selectByCondition(String queryString);

     //新增会员
     void add(Member member);

     //删除会员
     void deleteById(Integer id);

     //根据id查询会员
     Member findById(Integer id);

     //根据手机号查询会员
     Member findByTelephone(String telephone);

     //编辑会员
     void edit(Member member);

     //根据日期统计会员数，统计指定日期之前的会员数
     Integer findMemberCountBeforeDate(String date);

     //根据日期统计会员数
     Integer findMemberCountByDate(String date);

     //根据日期统计会员数，统计指定日期之后的会员数
     Integer findMemberCountAfterDate(String date);

     //总会员数
     Integer findMemberTotalCount();
}
