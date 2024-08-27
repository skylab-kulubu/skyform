package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.QuestionMessages;
import com.weblab.skyform.core.utilities.results.*;
import com.weblab.skyform.dataAccess.abstracts.QuestionDao;
import com.weblab.skyform.entities.*;
import com.weblab.skyform.entities.dtos.question.CreateQuestionDto;
import com.weblab.skyform.entities.dtos.question.GetQuestionDto;
import com.weblab.skyform.entities.dtos.question.GetQuestionRatingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionManager implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserService userService;

    @Autowired
    private FormService formService;

    @Override
    public Result addQuestion(CreateQuestionDto createQuestionDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserMail = authentication.getName();

        var userResult = userService.getUserByEmail(currentUserMail);

        if(!userResult.isSuccess()){
            return userResult;
        }

        var user = userResult.getData();

        var formResult = formService.getFormById(createQuestionDto.getFormId());

        if(!formResult.isSuccess()){
            return formResult;
        }

        String questionType = createQuestionDto.getQuestionType();

        if(!questionType.equals("TEXT") && !questionType.equals("DATE") && !questionType.equals("RATING")){
            return new ErrorResult(QuestionMessages.questionTypeNotValid);
        }

        Question question = null;

        if(questionType.equals("RATING")){
            question = new QuestionRating(createQuestionDto.getMaxRating());
            question.setQuestionText(createQuestionDto.getQuestionText());
            question.setQuestionType(QuestionType.TYPE_RATING);
            question.setCreator(user);
            question.setQuestionOrder(createQuestionDto.getQuestionOrder());
            question.setForm(formResult.getData());
            question.setRequired(createQuestionDto.isRequired());

        } else if (questionType.equals("TEXT")) {
            question = new QuestionText();
            question.setQuestionText(createQuestionDto.getQuestionText());
            question.setQuestionType(QuestionType.TYPE_TEXT);
            question.setCreator(user);
            question.setQuestionOrder(createQuestionDto.getQuestionOrder());
            question.setForm(formResult.getData());
            question.setRequired(createQuestionDto.isRequired());


        } else if (questionType.equals("DATE")){
        question = new QuestionDate();
        question.setQuestionText(createQuestionDto.getQuestionText());
        question.setQuestionType(QuestionType.TYPE_DATE);
        question.setCreator(user);
        question.setQuestionOrder(createQuestionDto.getQuestionOrder());
        question.setForm(formResult.getData());
        question.setRequired(createQuestionDto.isRequired());

        }

        questionDao.save(question);

        return new SuccessResult(QuestionMessages.questionAdded);
    }

    @Override
    public DataResult<Question> getQuestionById(int id) {
        var question = questionDao.findById(id);

        if(!question.isPresent()){
            return new ErrorDataResult<>(QuestionMessages.questionNotFound);
        }

        return new SuccessDataResult<>(question.get(), QuestionMessages.questionSuccessfullyBrought);
    }

    @Override
    public DataResult<GetQuestionDto> getQuestionDtoById(int id) {
        var result = questionDao.findById(id);

        if(!result.isPresent()){
            return new ErrorDataResult<>(QuestionMessages.questionNotFound);
        }

        var question = result.get();

        GetQuestionDto returnQuestion = null;

        if(question.getQuestionType().getValue().equals("RATING")){
            returnQuestion = new GetQuestionRatingDto((QuestionRating) question);
        }else if (question.getQuestionType().getValue().equals("DATE") || question.getQuestionType().getValue().equals("TEXT")){
            returnQuestion = new GetQuestionDto(question);
        }

        return new SuccessDataResult<>(returnQuestion, QuestionMessages.questionSuccessfullyBrought);


    }

    @Override
    public DataResult<List<Question>> getAllQuestions() {
        var questions = questionDao.findAll();

        if(questions.isEmpty()){
            return new ErrorDataResult<>(QuestionMessages.questionsNotFound);
        }

        return new SuccessDataResult<>(questions, QuestionMessages.questionsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetQuestionDto>> getAllQuestionsDto() {
        var questions = questionDao.findAll();

        if(questions.isEmpty()){
            return new ErrorDataResult<>(QuestionMessages.questionsNotFound);
        }

        List<GetQuestionDto> returnList = new GetQuestionDto().buildListGetQuestionDto(questions);

        return new SuccessDataResult<>(returnList, QuestionMessages.questionsSuccessfullyBrought);
    }
}
