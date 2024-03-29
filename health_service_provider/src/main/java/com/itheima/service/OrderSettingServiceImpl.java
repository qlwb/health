package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/21 10:05
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    //批量添加
    public void add(List<OrderSetting> list) {
        if(list != null && list.size() > 0) {
            for (OrderSetting orderSetting : list) {
                //检查此数据（日期）是否存在
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    //已经存在，执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    //不存在，执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    //根据给定的月份查询该月所有的预约设置  月份 格式:yyyy-MM
    public List<Map<String, Object>> getOrderSettingByMonth(String date) {
        //{ date: 3, number: 100, reservations: 0 } 由于需要返回该格式的json数据 所以需要把实体类转换为Map集合
        String beginDate=date+"-01";
        //结束日期为31号 则无论是哪月都可以查询出全部的预约设置
        String endDate=date+"-31";
        //获取当前月份的全部预约设置
        List<OrderSetting> orderSettingList = orderSettingDao.queryOrderSettingByMonth(beginDate, endDate);
        //打印orderSettingList
        System.out.println("orderSettingList:{}"+orderSettingList);
        //实体类转换为Map集合
        List<Map<String, Object>> mapList=new ArrayList<>();
        //循环遍历
        for (OrderSetting orderSetting : orderSettingList) {
            /**
             * 定义map集合  不能放在循环外定义 因为它是把所有的数据都添加到List集合中
             * 而不是封装完数据直接操作数据库 所有必须保证List集合中每一个Map集合都是不同的
             * 这样才不会互相干扰数据
             */
            Map<String,Object> map=new HashMap<>();
            //封装  getDay():获取周中的某一天
            map.put("date",orderSetting.getOrderDate().getDate());
//            System.out.println("getDate()"+orderSetting.getOrderDate().getDate());
//            System.out.println("getDay()"+orderSetting.getOrderDate().getDay());
            map.put("number",orderSetting.getNumber());//可预约人数
            map.put("reservations",orderSetting.getReservations());//已预约人数
            //把map集合添加到list集合中
            mapList.add(map);
        }
        //打印mapList
        System.out.println("mapList:{}"+mapList);
        //把封装后的数据返回
        return mapList;
    }

    //预约设置
    public void editNumberByDate(OrderSetting orderSetting) {
        //首先判断该日期对应的预约设置是否存在
        Long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        System.out.println("count:{}"+count);
        if (count>0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else {
            //不存在 执行新增操作
            orderSettingDao.add(orderSetting);
        }
    }
}
