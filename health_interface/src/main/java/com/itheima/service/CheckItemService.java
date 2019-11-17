package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;


/**
 * @Author: dxw
 * @Date: 2019/11/16 21:39
 */

//检查项服务接口
public interface CheckItemService {

    //新增检查项
     void add(CheckItem checkItem);

     //分页查询
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    //根据id查询单个检查项
    CheckItem findById(Integer id);

    //修改检查项信息
    void edit(CheckItem checkItem);

    //根据id删除单个检查项
    void delete(Integer id);
}
