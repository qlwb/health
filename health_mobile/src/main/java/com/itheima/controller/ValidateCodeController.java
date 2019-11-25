package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @Author: dxw
 * @Date: 2019/11/22 14:53
 */

//短信验证码
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Resource
    private JedisPool jedisPool;

    @RequestMapping("send4Order")
    public Result send4Order(String telephone) {
        //生成4位验证码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            //发送短信
//            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code.toString());
            System.out.println("预约发送的手机验证码为：" + code);
            //把验证码保存到缓存中 使用SENDTYPE_ORDER+手机号作为缓存中的验证码标识 有效时间5分钟
            jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER, 5 * 60, code.toString());
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }

    @RequestMapping("/send6Login")
    public Result send6Login(String telephone) {
        Integer code = ValidateCodeUtils.generateValidateCode(6);//生成6位数字验 证码
        try {
            //发送短信
//            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code.toString());
            System.out.println("登录验证码："+code);
        } catch (Exception e) {
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_LOGIN, 5 * 60, code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
