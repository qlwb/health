package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dxw
 * @Date: 2019/11/26 21:06
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    //分页查询预约列表
    @RequestMapping("/findPage")
    public PageResult findOrderList(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = orderService.pageQuery(queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(), queryPageBean.getQueryString());
        return pageResult;
    }

    @RequestMapping("/confirmOrder")
    public Result confirmOrder(Integer id){
        try {
            orderService.confirmOrder(id);
        } catch (Exception e) {
            return new Result(false, MessageConstant.CONFIRM_ORDER_STATUS_FAIL);
        }
        return new Result(true, MessageConstant.CONFIRM_ORDER_STATUS_SUCCESS);
    }

}
