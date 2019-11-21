package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConst;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: dxw
 * @Date: 2019/11/17 21:59
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;

    //分页查询检查套餐
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //分页助手设置当前页，每页显示条数
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setmealDao.selectByCondition(queryString);
        //封装返回的结果对象
        return new PageResult(page.getTotal(), page.getResult());
    }

   //新增检查套餐
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        //将图片名称保存到Redis
        savePic2Redis(setmeal.getImg());
    }

    //根据id查询单个套餐信息
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    //根据套餐id查询对应的检查组id
    public List<Integer> checkGroupIdBySetMealId(Integer id) {
        return setmealDao.checkGroupIdBySetMealId(id);
    }

    //编辑套餐
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.update(setmeal);
        //删除与套餐绑定的检查组
        setmealDao.deleteAssociation(setmeal.getId());
        //重新建立联系
        setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        //将图片名称保存到Redis
        savePic2Redis(setmeal.getImg());
    }


    private void savePic2Redis(String img) {
        jedisPool.getResource().sadd(RedisConst.SETMEAL_PIC_DB_RESOURCES,img);
    }

    private void setSetmealAndCheckGroup(Integer setmealId, Integer[] checkgroupIds) {
        if(checkgroupIds != null && checkgroupIds.length > 0){
            for (Integer checkGroupId : checkgroupIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("setmeal_id",setmealId);
                map.put("checkgroup_id",checkGroupId);
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
    }


}