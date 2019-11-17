package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;

/**
 * @Author: dxw
 * @Date: 2019/11/17 17:22
 */
public interface CheckGroupService {

    //按条件分页显示
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    //新增检查组
    void add(CheckGroup checkGroup, Integer[] checkitemIds);
}
