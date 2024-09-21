package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.abstracts.ResponseService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.ResponseMessages;
import com.weblab.skyform.core.utilities.excel.ExcelFileHelper;
import com.weblab.skyform.core.utilities.results.*;
import com.weblab.skyform.dataAccess.abstracts.ResponseDao;
import com.weblab.skyform.entities.*;
import com.weblab.skyform.entities.dtos.response.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ResponseManager implements ResponseService {

    private final ResponseDao responseDao;

    private final UserService userService;

    private final QuestionService questionService;

    private final FormService formService;

    private final ExcelFileHelper excelFileHelper;


    public ResponseManager(QuestionService questionService, ResponseDao responseDao, UserService userService, FormService formService,@Lazy ExcelFileHelper excelFileHelper) {
        this.questionService = questionService;
        this.responseDao = responseDao;
        this.userService = userService;
        this.formService = formService;
        this.excelFileHelper = excelFileHelper;
    }

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
    public Result addResponses(List<CreateResponseDto> createResponseDtos, int formId) {
        var userResult = userService.getLoggedInUser();
        User user;
        if (userResult.isSuccess()) {
            user = userResult.getData();
        } else {
            user = null;
        }

        var sessionId = UUID.randomUUID().toString();

        List<Response> responseList = new ArrayList<>();

        var questionList = questionService.getQuestionsByFormId(formId).getData();

       for (var createResponseDto : createResponseDtos) {
           var question = questionList.stream().filter(q -> q.getId() == createResponseDto.getQuestionId()).findFirst().orElse(null);

           if (question == null) {
               return new ErrorResult(ResponseMessages.questionNotFound);
           }

           if (!question.getQuestionType().getValue().equals(createResponseDto.getResponseType())) {
               return new ErrorResult(ResponseMessages.responseTypeIsNotEqualToQuestionType);
           }

           var questionType = question.getQuestionType().getValue();

           if (questionType.equals("TEXT") && createResponseDto.getTextResponse() == null && question.isRequired()) {
               return new ErrorResult(ResponseMessages.responseTextIsRequired);
           }

           if (questionType.equals("DATE") && createResponseDto.getDateResponse() == null && question.isRequired()) {
               return new ErrorResult(ResponseMessages.responseDateIsRequired);
           }

           if (questionType.equals("RATING") && createResponseDto.getRatingResponse() == 0 && question.isRequired()) {
               return new ErrorResult(ResponseMessages.responseRatingIsRequired);
           }

           Response response = null;

           if (questionType.equals("TEXT")) {
               response = new ResponseText(createResponseDto.getTextResponse());
           } else if (questionType.equals("DATE")) {
               response = new ResponseDate(createResponseDto.getDateResponse());
           } else if (questionType.equals("RATING")) {
               response = new ResponseRating(createResponseDto.getRatingResponse());
           }

           response.setResponder(user);
           response.setQuestion(question);
           response.setResponseSession(sessionId);

           responseList.add(response);
       }

        responseDao.saveAll(responseList);

        return new SuccessResult(ResponseMessages.responsesAdded);

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

        if(!response.isPresent()){
            return new ErrorDataResult<>(ResponseMessages.responseNotFound);
        }

        return new SuccessDataResult<>(response.get(), ResponseMessages.responseSuccessfullyBrought);

    }

    @Override
    public DataResult<GetResponseDto> getResponseDtoById(int id) {
        var result = responseDao.findById(id);

        if(!result.isPresent()){
            return new ErrorDataResult<>(ResponseMessages.responseNotFound);
        }

        var response = result.get();

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

    @Override
    public DataResult<List<GetResponseDto>> getResponsesByResponseSession(String responseSession) {
        var responses = responseDao.findAllByResponseSession(responseSession);

        if(responses.isEmpty()){
            return new ErrorDataResult<>(ResponseMessages.responsesNotFound);
        }

        List<GetResponseDto> listGetResponseDto = new GetResponseDto().buildListGetResponseDto(responses.get());

        return new SuccessDataResult<>(listGetResponseDto, ResponseMessages.responsesSuccessfullyBrought);
    }

    @Override
    public DataResult<List<Response>> getResponsesByFormId(int formId) {
        var responses = responseDao.findAllByQuestion_FormId(formId);

        if (responses.isEmpty()) {
            return new ErrorDataResult<>(ResponseMessages.responsesNotFound);
        }

        return new SuccessDataResult<>(responses.get(), ResponseMessages.responsesSuccessfullyBrought);
    }

    @Override
    public DataResult<byte[]> exportFormResponsesToExcelByFormId(int formId) {
        var formResult = formService.getFormById(formId);
        if (!formResult.isSuccess()) {
            return new ErrorDataResult<>(null, formResult.getMessage());
        }
        var form = formResult.getData();


        var excelFileResponse = excelFileHelper.exportFormResponsesToExcel(form.getId());
        if (!excelFileResponse.isSuccess()){
            return new ErrorDataResult<>(null, excelFileResponse.getMessage());
        }

        return new SuccessDataResult<byte[]>(excelFileResponse.getData(), "Form responses exported to excel file successfully.");
    }
}
