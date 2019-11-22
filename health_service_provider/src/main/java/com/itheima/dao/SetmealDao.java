package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/17 22:00
 */
public interface SetmealDao {

    Page<Setmeal> selectByCondition(String queryString);

    void add(Setmeal setmeal);

    void setSetmealAndCheckGroup(Map<String, Integer> map);

    //根据id查询单个套餐信息
    Setmeal findById(Integer id);

    //根据套餐id查询对应的检查组id
    List<Integer> checkGroupIdBySetMealId(Integer id);

    //编辑套餐信息
    void update(Setmeal setmeal);

    //删除与套餐绑定的检查组
    void deleteAssociation(Integer id);

    //删除检查套餐信息
    void delete(Integer id);

    List<Setmeal> findAll();
}
