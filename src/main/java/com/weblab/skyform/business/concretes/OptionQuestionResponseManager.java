package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.OptionQuestionResponseService;
import com.weblab.skyform.business.abstracts.QuestionOptionService;
import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessDataResult;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.OptionQuestionResponseDao;
import com.weblab.skyform.entities.OptionQuestionResponse;
import com.weblab.skyform.entities.dtos.OptionQuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionQuestionResponseManager implements OptionQuestionResponseService {

    private OptionQuestionResponseDao optionQuestionResponseDao;

    private UserService userService;

    private QuestionService questionService;

    private QuestionOptionService questionOptionService;

    @Autowired
    public OptionQuestionResponseManager(OptionQuestionResponseDao optionQuestionResponseDao,
                                         UserService userService,
                                         QuestionService questionService,
                                         QuestionOptionService questionOptionService) {
        this.optionQuestionResponseDao = optionQuestionResponseDao;
        this.userService = userService;
        this.questionService = questionService;
        this.questionOptionService = questionOptionService;
    }

    @Override
    public Result addOptionQuestion(OptionQuestionResponseDto optionQuestionResponseDto) {
        OptionQuestionResponse optionQuestionResponse = OptionQuestionResponse.builder()
                .responder(userService.getUserByUserId(optionQuestionResponseDto.getResponderId()).getData())
                .question(questionService.getQuestionById(optionQuestionResponseDto.getQuestionId()).getData())
                .chosenOption(questionOptionService.getQuestionOptionById(optionQuestionResponseDto.getChosenOptionId()).getData())
                .build();

        optionQuestionResponseDao.save(optionQuestionResponse);

        return new SuccessResult(Messages.addOptionQuestionSuccess);

    }

    @Override
    public DataResult<List<OptionQuestionResponse>> getAllOptionQuestionResponses() {
        var result = optionQuestionResponseDao.findAll();

        return new SuccessDataResult<List<OptionQuestionResponse>>(result, Messages.getAllOptionQuestionResponses);
    }

    @Override
    public DataResult<OptionQuestionResponse> getOptionQuestionResponseById(int id) {
        var result = optionQuestionResponseDao.findById(id);

        return new SuccessDataResult<OptionQuestionResponse>(result, Messages.getOptionQuestionResponseById);
    }
}
