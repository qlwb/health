package com.itheima;

import com.itheima.constant.RedisConst;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;


import java.util.Date;
import java.util.Set;

/**
 * @Author: dxw
 * @Date: 2019/11/19 15:09
 */
//自定义Job，实现定时清理垃圾图片
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        System.out.println(1111111111);
        //根据Redis中保存的两个set集合进行差值计算，获取垃圾图片名称集合
        Set<String> strings = jedisPool.getResource().sdiff(RedisConst.SETMEAL_PIC_RESOURCES,RedisConst.SETMEAL_PIC_DB_RESOURCES);
        if(strings!=null){
            for (String fileName : strings) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(fileName);
                //从Redis集合中删除图片名称
                jedisPool.getResource().srem(RedisConst.SETMEAL_PIC_RESOURCES,fileName);
                System.out.println("图片删除成功"+new Date());
            }
        }
    }
}
