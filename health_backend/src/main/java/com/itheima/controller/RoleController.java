package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Role;
import com.itheima.service.RoleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/25 19:18
 */
//角色管理
@RestController
@RequestMapping("/role")
public class RoleController {

    @Reference
    private RoleService roleService;

    //模糊查询角色并分页
    @RequestMapping("/queryRoleByPage")
    public PageResult queryRoleByPage(@RequestBody QueryPageBean queryPageBean) {
        return roleService.queryRoleByPage(queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(), queryPageBean.getQueryString());
    }

    @RequestMapping("/insertRole/{permissionIds}/{menuIds}")
    public Result insertRole(@RequestBody Role role, @PathVariable Integer[] permissionIds, @PathVariable Integer[] menuIds ){
        try{
            roleService.insertRole(role,permissionIds,menuIds);
            return new Result(true, MessageConstant.ADD_ROLE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_ROLE_FAIL);
        }
    }

    @RequestMapping("/queryRoleById/{id}")
    public Result queryRoleById(@PathVariable Integer id){
        try{
            Role role=roleService.queryRoleById(id);
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,role);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ROLE_FAIL);
        }
    }

    /**
     * 更新角色
     * @param role
     * @param permissionIds
     * @param menuIds
     * @return
     */
    @RequestMapping("/updateRole/{permissionIds}/{menuIds}")
    public Result updateRole(@RequestBody Role role,@PathVariable Integer[] permissionIds,@PathVariable Integer[] menuIds){
        try{
            roleService.updateRole(role,permissionIds,menuIds);
            return new Result(true,MessageConstant.EDIT_ROLE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_ROLE_FAIL);
        }
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteRoleById/{id}")
    public Result deleteRoleById(@PathVariable Integer id){
        try{
            roleService.deleteRoleById(id);
            return new Result(true,MessageConstant.DELETE_ROLE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_ROLE_FAIL);
        }
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Role> list = roleService.findAll();
            return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ROLE_FAIL);
        }
    }


    @RequestMapping("/findRoleIdsByUserId")
    public Result findRoleIdsByUserId(Integer id){
        List<Integer> roleIds = null;
        try {
            roleIds = roleService.findRoleIdsByUserId(id);
            return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS, roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ROLE_FAIL);
        }
    }

}
