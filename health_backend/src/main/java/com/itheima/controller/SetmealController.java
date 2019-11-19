package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConst;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @Author: dxw
 * @Date: 2019/11/17 21:56
 */

/**
 * 体检套餐管理
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery( queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(), queryPageBean.getQueryString());
        return pageResult;
    }

    @RequestMapping("upload")
    public Result upload(MultipartFile imgFile){
        try{
            //获取文件的名称
            String originalFilename = imgFile.getOriginalFilename();
            //设置文件存储的名称
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀
            String suffix = originalFilename.substring(lastIndexOf - 1);
            //使用UUID随机产生文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            System.out.println(fileName);
            //上传图片到七牛云
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            //将上传图片名称存入Redis,基于Redis的set集合存储
            jedisPool.getResource().sadd(RedisConst.SETMEAL_PIC_RESOURCES,fileName);
            //返回数据
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
            setmealService.add(setmeal,checkgroupIds);
        }catch (Exception e){
            //新增套餐失败
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增套餐成功
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

}
