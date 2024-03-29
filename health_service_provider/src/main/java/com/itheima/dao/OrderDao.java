package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
     void add(Order order);

     //查询某日期是否存在某会员预约了某套餐
     List<Order> findByCondition(Order order);

     //根据预约id查询预约信息，包括体检人信息、套餐信息
     Map findById4Detail(Integer id);

     //根据日期统计预约数
     Integer findOrderCountByDate(String date);

     //根据日期统计预约数，统计指定日期之后的预约数
     Integer findOrderCountAfterDate(String date);

     //根据日期统计到诊数
     Integer findVisitsCountByDate(String date);

     //根据日期统计到诊数，统计指定日期之后的到诊数
     Integer findVisitsCountAfterDate(String date);

     //热门套餐，查询前5条
     List<Map> findHotSetmeal();

     //分页
     Page<Map<String,Object>> selectByCondition(String queryString);

     //确认到诊
    void confirmOrderStatus(Integer id);

    //后台查找订单
     Map findByIdAdmin(Integer id);

     //根据订单id查询套餐id
    List<Integer> findMealIdByOrderId(Integer id);

    //删除订单
    void deleteById(Integer id);
}
