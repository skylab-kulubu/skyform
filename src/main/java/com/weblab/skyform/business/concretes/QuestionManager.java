package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.abstracts.QuestionTypeService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessDataResult;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.QuestionDao;
import com.weblab.skyform.entities.Question;
import com.weblab.skyform.entities.dtos.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionManager implements QuestionService {

    private QuestionDao questionDao;
    private QuestionTypeService questionTypeService;
    private FormService formService;

    @Autowired
    public QuestionManager(QuestionDao questionDao, QuestionTypeService questionTypeService, FormService formService){
        this.questionDao = questionDao;
        this.questionTypeService = questionTypeService;
        this.formService = formService;
    }


    public DataResult<List<Question>> getQuestions(){
        var result = questionDao.findAll();
        return new SuccessDataResult<List<Question>>(result, Messages.getQuestionSuccess);
    }

    @Override
    public DataResult<List<Question>> getQuestionsByFormId(int formId) {
        var result = questionDao.getQuestionsByFormId(formId);
        return new SuccessDataResult<List<Question>>(result, Messages.getQuestionsByFormIdSuccess);
    }

    @Override
    public DataResult<Question> getQuestionById(int id) {
        var result = questionDao.getQuestionById(id);
        return new SuccessDataResult<Question>(result, Messages.getQuestionByIdSuccess);
    }

    @Override
    public Result addQuestion(QuestionDto questionDto) {
        Question question = Question.builder().
                form(formService.getFormById(questionDto.getFormId()).getData())
                .questionText(questionDto.getQuestionText())
                .questionType(questionTypeService.getQuestionTypeById(questionDto.getQuestionTypeId()).getData())
                .questionOrder(questionDto.getQuestionOrder())
                .build();

        questionDao.save(question);
        return new SuccessResult(Messages.questionAddSuccess);
    }



}
