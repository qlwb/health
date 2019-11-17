package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

/**
 * @Author: dxw
 * @Date: 2019/11/17 17:23
 */
public interface CheckGroupDao {

    //按条件分页显示
    Page<CheckGroup> selectByCondition(String queryString);
}
