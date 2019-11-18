package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/17 17:22
 */
public interface CheckGroupService {

    //按条件分页显示
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    //新增检查组
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    //根据id查询单个检查组信息
    CheckGroup findById(Integer id);

    //根据检查组的id查询检查组中包含的检查项id
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    //编辑检查组信息
    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    //查询所有检查组信息
    List<CheckGroup> findAll();
}
