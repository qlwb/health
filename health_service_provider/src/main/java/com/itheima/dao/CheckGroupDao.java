package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

import java.util.List;
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

    //根据id查询单个检查组信息
    CheckGroup findById(Integer id);

    //根据检查组的id查询检查组中包含的检查项id
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    //根据检查组id删除中间表数据（清理原有关联关系）
    void deleteAssociation(Integer id);

    //更新检查组基本信息
    void update(CheckGroup checkGroup);

    //查询所有检查组信息
    List<CheckGroup> findAll();


    //根据id删除检查组
    void deleteById(Integer id);
}
