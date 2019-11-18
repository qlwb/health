package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;

/**
 * @Author: dxw
 * @Date: 2019/11/17 22:00
 */
public interface SetmealDao {

    Page<Setmeal> selectByCondition(String queryString);
}
