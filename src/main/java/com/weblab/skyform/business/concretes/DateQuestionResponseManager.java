package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.DateQuestionResponseService;
import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.DateQuestionResponseDao;
import com.weblab.skyform.entities.DateQuestionResponse;
import com.weblab.skyform.entities.OptionQuestionResponse;
import com.weblab.skyform.entities.dtos.DateQuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateQuestionResponseManager implements DateQuestionResponseService {

    private DateQuestionResponseDao dateQuestionResponseDao;

    private QuestionService questionService;
    private UserService userService;

    @Autowired
    public DateQuestionResponseManager(DateQuestionResponseDao dateQuestionResponseDao,
                                       QuestionService questionService,
                                       UserService userService) {
        this.dateQuestionResponseDao = dateQuestionResponseDao;
        this.questionService = questionService;
        this.userService = userService;
    }

    @Override
    public Result addDateQuestionResponse(DateQuestionResponseDto dateQuestionResponseDto) {
        DateQuestionResponse dateQuestionResponse = DateQuestionResponse.builder().
                responder(userService.getUserByUserId(dateQuestionResponseDto.getResponderId()).getData()).
                question(questionService.getQuestionById(dateQuestionResponseDto.getQuestionId()).getData()).
                date(dateQuestionResponseDto.getDate()).build();

            dateQuestionResponseDao.save(dateQuestionResponse);
            return new SuccessResult(Messages.dateQuestionResponseAddSuccess);

    }

    @Override
    public DataResult<List<OptionQuestionResponse>> getAllDateQuestionResponses() {
        return null;
    }

    @Override
    public DataResult<OptionQuestionResponse> getDateQuestionResponseById(int id) {
        return null;
    }
}
