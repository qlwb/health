package com.itheima.dao;

import com.itheima.pojo.Question;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/28 21:19
 */
public interface QuestionDao {
    List<Question> findByQuestionnaireId(int id);
}
