package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: dxw
 * @Date: 2019/11/17 21:59
 */
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //分页助手设置当前页，每页显示条数
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.selectByCondition(queryString);
        //封装返回的结果对象
        return new PageResult(page.getTotal(),page.getResult());
    }
}
