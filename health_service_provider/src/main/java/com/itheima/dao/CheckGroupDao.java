package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/17 17:23
 */
public interface CheckGroupDao {

    //按条件分页显示
    Page<CheckGroup> selectByCondition(String queryString);

    //新增检查组
    void add(CheckGroup checkGroup);

    //检查组和检查项建立联系
    void setCheckGroupAndCheckItem(Map<String, Integer> map);
}
