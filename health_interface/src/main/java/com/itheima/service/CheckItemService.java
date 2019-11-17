package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;


/**
 * @Author: dxw
 * @Date: 2019/11/16 21:39
 */

//检查项服务接口
public interface CheckItemService {
     void add(CheckItem checkItem);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);
}
