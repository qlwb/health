package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: dxw
 * @Date: 2019/11/26 22:50
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
    private MemberService memberService;

    /**
     * 分页查询所有会员
     * @param queryPageBean 分页查询参数对象
     * @return
     */
    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = memberService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString());
        return pageResult;
    }

    /**
     * 新增会员
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody Member member) {
        Date regTime = new Date();
        member.setRegTime(regTime);
        try {
            memberService.add(member);
            return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_MEMBER_FAIL);
        }
    }



}