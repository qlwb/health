package com.itheima.service;

import com.itheima.entity.PageResult;

/**
 * @Author: dxw
 * @Date: 2019/11/17 17:22
 */
public interface CheckGroupService {

    //按条件分页显示
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}
