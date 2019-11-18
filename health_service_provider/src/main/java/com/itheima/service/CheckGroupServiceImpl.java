package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/17 17:23
 */
@Service
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    //按条件分页显示
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //分页助手设置当前页，每页显示条数
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.selectByCondition(queryString);
        //封装返回的结果对象
        return new PageResult(page.getTotal(),page.getResult());
    }

   //新增检查组，同时需要设置检查组合和检查项的关联关系
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        setCheckGroupAndCheackItem(checkGroup.getId(),checkitemIds);
    }

    //根据id查询单个检查组信息
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    //根据检查组的id查询检查组中包含的检查项id
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    //编辑检查组信息 ，同时需要更新和检查项的关联关系
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        //根据检查组id删除中间表数据（清理原有关联关系）
        checkGroupDao.deleteAssociation(checkGroup.getId());
        //向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
        setCheckGroupAndCheackItem(checkGroup.getId(),checkitemIds);
        //更新检查组基本信息
        checkGroupDao.update(checkGroup);
    }

    //查询所有检查组信息
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }


    private void setCheckGroupAndCheackItem(Integer checkGroupId, Integer[] checkitemIds) {
        if(checkitemIds != null && checkitemIds.length > 0){
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("checkgroup_id",checkGroupId);
                map.put("checkitem_id",checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

}
