package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.QuestionDao;
import com.itheima.dao.QuestionnaireDao;
import com.itheima.pojo.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: dxw
 * @Date: 2019/11/28 20:14
 */
@Service(interfaceClass = QuestionnaireService.class)
@Transactional
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    private QuestionnaireDao questionnaireDao;
    @Autowired
    private QuestionDao questionDao;

    //查询单个评估类型及其问题
    public Questionnaire findById(int id) {
        Questionnaire questionnaire = questionnaireDao.findById(id);
        questionnaire.setQuestions(questionDao.findByQuestionnaireId(id));
        return questionnaire;
    }

    //查询所有评估类型
    public List<Questionnaire> findAll() {
        List<Questionnaire> questionnaireList = questionnaireDao.findAll();
        for (Questionnaire questionnaire : questionnaireList) {
            questionnaire.setQuestions(questionDao.findByQuestionnaireId(questionnaire.getId()));
        }
        return questionnaireList;
    }
}
