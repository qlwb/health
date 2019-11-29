package com.itheima.service;

import com.itheima.pojo.Questionnaire;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/28 20:13
 */
public interface QuestionnaireService {
    //查询单个评估类型及其问题
    Questionnaire findById(int id);

    //查询所有评估类型
    List<Questionnaire> findAll();
}
