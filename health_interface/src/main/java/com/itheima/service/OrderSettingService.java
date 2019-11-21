package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/21 10:04
 */
public interface OrderSettingService {
    //批量添加
    void add(List<OrderSetting> orderSettingList);

    //根据给定的月份查询该月所有的预约设置  月份 格式:yyyy-MM
    List<Map<String,Object>> getOrderSettingByMonth(String date);
}
