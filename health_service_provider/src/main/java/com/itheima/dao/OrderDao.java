package com.itheima.dao;

import com.itheima.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
     void add(Order order);

     List<Order> findByCondition(Order order);

     //根据预约id查询预约信息，包括体检人信息、套餐信息
     Map findById4Detail(Integer id);

     Integer findOrderCountByDate(String date);

     Integer findOrderCountAfterDate(String date);

     Integer findVisitsCountByDate(String date);

     Integer findVisitsCountAfterDate(String date);

     List<Map> findHotSetmeal();
}
