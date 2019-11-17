package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;

import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/16 21:41
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    //新增检查项
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    //分页查询检查项
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //分页助手设置当前页，每页显示条数
        PageHelper.startPage(currentPage,pageSize);
        //调用dao
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        //封装返回的结果对象
        return new PageResult(page.getTotal(),page.getResult());
    }

    //根据id查询单个检查项
    public CheckItem findById(Integer id) {
        return  checkItemDao.findById(id);
    }

    //修改检查项信息
    public void edit(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    //根据id删除单个检查项
    public void delete(Integer id)  {
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被引用，不能删除");
        }
        checkItemDao.deleteById(id);
    }

    //查询所有
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
