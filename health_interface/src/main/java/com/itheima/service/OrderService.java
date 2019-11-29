package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;

import java.util.List;
import java.util.Map; /**
 * @Author: dxw
 * @Date: 2019/11/22 15:16
 */
//体检预约服务接口
public interface OrderService {
    //体检预约
    Result order(Map map) throws Exception;

    //根据id查询预约信息，包括套餐信息和会员信息
    Map findById(Integer id) throws Exception;

    //分页
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    //确认到诊
    void confirmOrder(Integer id);

    //体检预约(电话预约)
    Result orderByAdmin(Map map, Integer[] setmealIds) throws Exception;

    //后台查找订单
    Map findByIdAdmin(Integer id);

    //根据订单id查询套餐id
    List<Integer> setMealIdByOrderId(Integer id);

    void update(Map map, Integer[] setmealIds) throws Exception;

    void delete(Integer id);
}
