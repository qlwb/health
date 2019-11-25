package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import com.itheima.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/22 15:05
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;

    //体检预约
    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody Map map) {//没有实体类对象能与之相对应
        String telephone = (String) map.get("telephone");
        if(telephone==null){
            //手机号为空，给出手机号和验证码都不能为空的提示
            return new Result(false, MessageConstant.TELEPHONE_VALIDATECODE_NOTNULL);
        }
        //从Redis中获取缓存的验证码，key为手机号 +RedisConstant.SENDTYPE_ORDER
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validateCode = (String) map.get("validateCode");
        //校验手机验证码
        if (codeInRedis == null || !codeInRedis.equals(validateCode)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Result result = null;
        //调用体检预约服务
        try {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result = orderService.order(map);
        } catch (Exception e) {
            e.printStackTrace();
            //预约失败
            return result;
        }
        if (result.isFlag()) {
            //预约成功，发送短信通知
            String orderDate = (String) map.get("orderDate");
            try {
                SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, telephone, orderDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //根据id查询预约信息，包括套餐信息和会员信息
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Map map = orderService.findById(id);
            //查询预约信息成功
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            //查询预约信息失败
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}