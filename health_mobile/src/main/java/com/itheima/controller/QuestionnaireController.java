package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Questionnaire;
import com.itheima.service.MemberService;
import com.itheima.service.QuestionnaireService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/28 20:08
 */
//问卷调查
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Reference
    private QuestionnaireService questionnaireService;


    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<Questionnaire> list = questionnaireService.findAll();
            return new Result(true, MessageConstant.QUERY_QUESTIONNAIRE_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_QUESTIONNAIRE_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(int id) {
        try {
            Questionnaire questionnaire = questionnaireService.findById(id);
            return new Result(true, MessageConstant.QUERY_QUESTIONNAIRE_SUCCESS, questionnaire);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_QUESTIONNAIRE_FAIL);
        }
    }
}
