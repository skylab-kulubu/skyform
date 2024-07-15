package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.abstracts.ResponseService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.ResponseMessages;
import com.weblab.skyform.core.utilities.results.*;
import com.weblab.skyform.dataAccess.abstracts.ResponseDao;
import com.weblab.skyform.entities.Response;
import com.weblab.skyform.entities.ResponseDate;
import com.weblab.skyform.entities.ResponseRating;
import com.weblab.skyform.entities.ResponseText;
import com.weblab.skyform.entities.dtos.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseManager implements ResponseService {

    @Autowired
    private ResponseDao responseDao;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;


    @Override
    public Result addResponse(CreateResponseDto createResponseDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserMail = authentication.getName();

        var userResult = userService.getUserByEmail(currentUserMail);

        if (!userResult.isSuccess()) {
            return userResult;
        }

        var user = userResult.getData();

        var questionResult = questionService.getQuestionById(createResponseDto.getQuestionId());

        if (!questionResult.isSuccess()) {
            return questionResult;
        }

        var question = questionResult.getData();

        if (!question.getQuestionType().getValue().equals(createResponseDto.getResponseType())) {
            return new ErrorResult(ResponseMessages.responseTypeIsNotEqualToQuestionType);
        }

        var questionType = question.getQuestionType().getValue();


        if(questionType.equals("TEXT") && createResponseDto.getTextResponse() == null && question.isRequired()){
            return new ErrorResult(ResponseMessages.responseTextIsRequired);
        }

        if(questionType.equals("DATE") && createResponseDto.getDateResponse() == null && question.isRequired()){
            return new ErrorResult(ResponseMessages.responseDateIsRequired);
        }

        if(questionType.equals("RATING") && createResponseDto.getRatingResponse() == 0 && question.isRequired()){
            return new ErrorResult(ResponseMessages.responseRatingIsRequired);
        }


        if (questionType.equals("TEXT")) {
            Response response = new ResponseText(createResponseDto.getTextResponse());
            response.setResponder(user);
            response.setQuestion(question);

            responseDao.save(response);
        } else if (questionType.equals("DATE")) {
            Response response = new ResponseDate(createResponseDto.getDateResponse());
            response.setResponder(user);
            response.setQuestion(question);

            responseDao.save(response);
        } else if (questionType.equals("RATING")) {
            Response response = new ResponseRating(createResponseDto.getRatingResponse());
            response.setResponder(user);
            response.setQuestion(question);

            responseDao.save(response);

        }

        return new SuccessResult(ResponseMessages.responseAdded);
    }

    @Override
    public DataResult<List<Response>> getAllResponses() {
        var responses = responseDao.findAll();

        if(responses.isEmpty()){
            return new ErrorDataResult<>(ResponseMessages.responsesNotFound);
        }

        return new SuccessDataResult<>(responses, ResponseMessages.responsesSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetResponseDto>> getAllResponsesDto() {
        var response = responseDao.findAll();

        if(response.isEmpty()){
            return new ErrorDataResult<>(ResponseMessages.responsesNotFound);
        }

        List<GetResponseDto> listGetResponseDto = new GetResponseDto().buildListGetResponseDto(response);

        return new SuccessDataResult<>(listGetResponseDto, ResponseMessages.responsesSuccessfullyBrought);
    }

    @Override
    public DataResult<Response> getResponseById(int id) {
        var response = responseDao.findById(id);

        if(response == null){
            return new ErrorDataResult<>(ResponseMessages.responseNotFound);
        }

        return new SuccessDataResult<>(response, ResponseMessages.responseSuccessfullyBrought);

    }

    @Override
    public DataResult<GetResponseDto> getResponseDtoById(int id) {
        var response = responseDao.findById(id);

        if(response == null){
            return new ErrorDataResult<>(ResponseMessages.responseNotFound);
        }

        GetResponseDto returnResponse = null;

        if(response.getQuestion().getQuestionType().getValue().equals("TEXT")) {
            returnResponse = new GetResponseTextDto((ResponseText) response);
        }else if (response.getQuestion().getQuestionType().getValue().equals("DATE")) {
            returnResponse = new GetResponseDateDto((ResponseDate) response);
        }else if (response.getQuestion().getQuestionType().getValue().equals("RATING")) {
            returnResponse = new GetResponseRatingDto((ResponseRating) response);
        }

        return new SuccessDataResult<>(returnResponse, ResponseMessages.responseSuccessfullyBrought);
    }
}
