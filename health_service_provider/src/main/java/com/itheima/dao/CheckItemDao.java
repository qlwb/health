package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/16 21:45
 */
public interface CheckItemDao {

    //新增检查项
    void add(CheckItem checkItem);

    //分页查询检查项
    Page<CheckItem> selectByCondition(String queryString);

    //根据id查找单个检查项
    CheckItem findById(Integer id);

    //根据id修改检查项信息
    void update(CheckItem checkItem);

    //根据检查项id查询中间关系表
    long findCountByCheckItemId(Integer id);

    //根据id删除单个检查项
    void deleteById(Integer id);

    //查询所有
    List<CheckItem> findAll();
}
