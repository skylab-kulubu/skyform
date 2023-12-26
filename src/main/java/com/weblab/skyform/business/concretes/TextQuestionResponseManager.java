package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.abstracts.TextQuestionResponseService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessDataResult;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.TextQuestionResponseDao;
import com.weblab.skyform.entities.TextQuestionResponse;
import com.weblab.skyform.entities.dtos.TextQuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextQuestionResponseManager implements TextQuestionResponseService {

    private TextQuestionResponseDao textQuestionResponseDao;

    private UserService userService;

    private QuestionService questionService;

    @Autowired
    public TextQuestionResponseManager(TextQuestionResponseDao textQuestionResponseDao,
                                       UserService userService,
                                       QuestionService questionService) {
        this.textQuestionResponseDao = textQuestionResponseDao;
        this.userService = userService;
        this.questionService = questionService;
    }

    @Override
    public Result addTextQuestion(TextQuestionResponseDto textQuestionResponseDto) {
        TextQuestionResponse textQuestionResponse = TextQuestionResponse.builder()
                .responder(userService.getUserByUserId(textQuestionResponseDto.getResponderId()).getData())
                .question(questionService.getQuestionById(textQuestionResponseDto.getQuestionId()).getData())
                .text(textQuestionResponseDto.getText()).build();

        textQuestionResponseDao.save(textQuestionResponse);

        return new SuccessResult(Messages.addTextQuestionSuccess);

    }

    @Override
    public DataResult<List<TextQuestionResponse>> getAllTextQuestionResponses() {
        var result = textQuestionResponseDao.findAll();

        return new SuccessDataResult<List<TextQuestionResponse>>(result, Messages.getAllTextQuestionResponsesSuccess);
    }

    @Override
    public DataResult<TextQuestionResponse> getTextQuestionResponseById(int id) {
        var result = textQuestionResponseDao.findById(id);

        return new SuccessDataResult<TextQuestionResponse>(result, Messages.getTextQuestionResponseById);
    }
}

