package com.itheima.dao;

import com.itheima.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/21 10:05
 */
public interface OrderSettingDao {
    //检查此数据（日期）是否存在
    long findCountByOrderDate(Date orderDate);

    //执行更新操作
    void editNumberByOrderDate(OrderSetting orderSetting);

    //执行添加操作
    void add(OrderSetting orderSetting);

    /**
     * 根据开始日期和结束日期查询中间所以的预约设置
     * @param beginDate 开始日期
     * @param endDate  结束日期
     * @return
     */
    List<OrderSetting> queryOrderSettingByMonth(@Param("beginDate") String beginDate, @Param("endDate") String endDate);




}
