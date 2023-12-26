package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.QuestionOptionService;
import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessDataResult;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.QuestionOptionDao;
import com.weblab.skyform.entities.QuestionOption;
import com.weblab.skyform.entities.dtos.QuestionOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionOptionManager implements QuestionOptionService {

    private QuestionOptionDao questionOptionDao;

    private QuestionService questionService;

    @Autowired
    public QuestionOptionManager(QuestionOptionDao questionOptionDao, QuestionService questionService) {
        this.questionOptionDao = questionOptionDao;
        this.questionService = questionService;
    }

    @Override
    public Result addQuestionOption(QuestionOptionDto questionOptionDto) {
        QuestionOption questionOption = QuestionOption.builder()
                .question(questionService.getQuestionById(questionOptionDto.getQuestionId()).getData())
                .optionOrder(questionOptionDto.getOptionOrder())
                .optionText(questionOptionDto.getOptionText())
                .build();

        questionOptionDao.save(questionOption);

        return new SuccessResult(Messages.addQuestionOptionSuccess);
    }

    @Override
    public DataResult<QuestionOption> getQuestionOptionById(int id) {
        var result = questionOptionDao.findById(id);

        return new SuccessDataResult<QuestionOption>(result, Messages.getQuestionOptionByIdSuccess);
    }

    @Override
    public DataResult<List<QuestionOption>> getAllQuestionOptions() {
        var result = questionOptionDao.findAll();

        return new SuccessDataResult<List<QuestionOption>>(result, Messages.getAllQuestionOptionsSuccess);
    }

    @Override
    public DataResult<List<QuestionOption>> getQuestionOptionsByQuestionId(int questionId) {
        var result = questionOptionDao.findAllByQuestionId(questionId);

        return new SuccessDataResult<List<QuestionOption>>(result, Messages.getQuestionOptionsByQuestionId);
    }
}
