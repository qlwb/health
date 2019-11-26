package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Permission;
import com.itheima.service.PermissionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 19:31
 */
//权限管理
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Reference
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Permission> list = permissionService.findAll();
            return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL);
        }
    }


    /**
     * 根据角色id查询中间表的权限id集合
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/queryPermissionIdsById/{roleId}")
    public Result queryPermissionIdsById(@PathVariable Integer roleId) {
        try {
            List<Integer> permissionIds = permissionService.queryPermissionIdsByRoleId(roleId);
            return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS, permissionIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL);
        }
    }
}
