package com.itheima.service;

import com.itheima.entity.PageResult;

/**
 * @Author: dxw
 * @Date: 2019/11/17 21:58
 */
public interface SetmealService {
     PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}
