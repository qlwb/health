package com.itheima.controller;

/**
 * @Author: dxw
 * @Date: 2019/11/16 21:37
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import com.itheima.entity.Result;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 体检检查项管理
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;
    //新增检查项

    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")//权限校验
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
        }catch (Exception e){
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    //分页查询
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")//权限校验
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery( queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(), queryPageBean.getQueryString());
        return pageResult;
    }


    //根据id查找单个检查项
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckItem checkItem = checkItemService.findById(id);
            if(checkItem==null){
                return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
            }
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //更加页面提交数据，修改检查项
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")//权限校验
    @RequestMapping("/update")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    //根据id删除单个检查项
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkItemService.delete(id);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    //查询所有
    @RequestMapping("/findAll")
    public Result findAll(){
        List<CheckItem> checkItemList = checkItemService.findAll();
        if(checkItemList != null && checkItemList.size() > 0){
            Result result = new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS);
            result.setData(checkItemList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
    }
}
