package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/22 21:19
 */

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    //判断当前用户是否为会员
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    //注册会员
    public void add(Member member) {
        if (member.getPassword() != null) {
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    //查询yyyy.MM的会员人数
    public List<Integer> findMemberCountByMonth(List<String> list) {
        List<Integer> numList = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for (String s : list) {
                s+=".31";//格式：2019.11.31
                Integer memberCount = memberDao.findMemberCountBeforeDate(s);
                numList.add(memberCount);
            }
        }
        return numList;
    }
}
