package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

/**
 * @Author: dxw
 * @Date: 2019/11/16 21:45
 */
public interface CheckItemDao {

    void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

    CheckItem findById(Integer id);

    void update(CheckItem checkItem);
}
