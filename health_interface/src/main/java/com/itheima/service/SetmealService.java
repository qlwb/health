package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/17 21:58
 */
public interface SetmealService {
    //分页查询检查套餐
     PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

     //新增检查套餐
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    //根据id查询单个套餐信息
    Setmeal findById(Integer id);

    //根据套餐id查询对应的检查组id
    List<Integer> checkGroupIdBySetMealId(Integer id);


    //编辑套餐
    void update(Setmeal setmeal, Integer[] checkgroupIds);
}
