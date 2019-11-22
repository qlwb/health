package com.itheima.service;

import com.itheima.entity.Result;

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
}
