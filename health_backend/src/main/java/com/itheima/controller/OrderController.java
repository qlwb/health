package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.pojo.Setmeal;
import com.itheima.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody Map map,Integer[] setmealIds) {//没有实体类对象能与之相对应
        String telephone = (String) map.get("telephone");
        Result result = null;
        //调用体检预约服务
        try {
            map.put("orderType", Order.ORDERTYPE_TELEPHONE);
            result = orderService.orderByAdmin(map,setmealIds);
        } catch (Exception e) {
            e.printStackTrace();
            //预约失败
            return result;
        }
        return result;
    }


    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Map map = orderService.findByIdAdmin(id);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }


    @RequestMapping("/setMealIdByOrderId")
    public Result setMealIdByOrderId(Integer id) {
        try {
            List<Integer> setmealIds = orderService.setMealIdByOrderId(id);
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, setmealIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }


    @RequestMapping("/update")
    public Result update(@RequestBody Map map, Integer[] setmealIds) {
        try {
            orderService.update(map, setmealIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_ORDER_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_ORDER_SUCCESS);
    }


    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            orderService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }

    @RequestMapping("/deleteAddressIdsByOrderId")
    public Result deleteAddressIdsByOrderId(Integer id) {
        try {
//            orderService.deleteAddressIdsByOrderId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }


}
