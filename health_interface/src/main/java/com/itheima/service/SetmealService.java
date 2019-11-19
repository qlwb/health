package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;

/**
 * @Author: dxw
 * @Date: 2019/11/17 21:58
 */
public interface SetmealService {
    //分页查询检查套餐
     PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

     //新增检查套餐
    void add(Setmeal setmeal, Integer[] checkgroupIds);
}
