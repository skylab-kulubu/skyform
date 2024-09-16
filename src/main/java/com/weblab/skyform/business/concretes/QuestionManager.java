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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionManager implements QuestionService {

    private final QuestionDao questionDao;

    private final UserService userService;

    private final FormService formService;

    public QuestionManager(FormService formService, QuestionDao questionDao, UserService userService) {
        this.formService = formService;
        this.questionDao = questionDao;
        this.userService = userService;
    }

    @Override
    public Result addQuestion(CreateQuestionDto createQuestionDto, int formId) {
        var userResult = userService.getLoggedInUser();
        if (!userResult.isSuccess()){
            return userResult;
        }
        var user = userResult.getData();

        var formResult = formService.getFormById(formId);
        if(!formResult.isSuccess()){
            return formResult;
        }

        if (CheckIfFormContainsQuestionWithOrder(formResult.getData(), createQuestionDto.getQuestionOrder())){
            return new ErrorResult(QuestionMessages.questionOrderAlreadyExists + " " + createQuestionDto.getQuestionOrder());
        }

        String questionType = createQuestionDto.getQuestionType();
        if (!CheckIfQuestionTypeValid(questionType)){
            return new ErrorResult(QuestionMessages.questionTypeNotValid);
        }

        var questionResult = createQuestionFromDto(createQuestionDto, formResult.getData(), user);
        if (!questionResult.isSuccess()){
            return questionResult;
        }


        var question = questionResult.getData();
        questionDao.save(question);

        return new SuccessResult(QuestionMessages.questionAdded);
    }

    private boolean CheckIfFormContainsQuestionWithOrder(Form data, int questionOrder) {
        return questionDao.findByFormIdAndQuestionOrder(data.getId(), questionOrder).isPresent();
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

    @Override
    public Result addQuestions(List<CreateQuestionDto> createQuestionDtos, int formId) {
        var userResult = userService.getLoggedInUser();
        if (!userResult.isSuccess()){
            return userResult;
        }
        var user = userResult.getData();

        var formResult = formService.getFormById(formId);
        if(!formResult.isSuccess()){
            return formResult;
        }

        List<Question> questionsToSave = new ArrayList<>();

        for (var questionDto: createQuestionDtos){

            var questionResult = createQuestionFromDto(questionDto, formResult.getData(), user);

            if (!questionResult.isSuccess()){
                return questionResult;
            }

            if (CheckIfFormContainsQuestionWithOrder(formResult.getData(), questionDto.getQuestionOrder())){
                return new ErrorResult(QuestionMessages.questionOrderAlreadyExists + " " + questionDto.getQuestionOrder());
            }

            questionsToSave.add(questionResult.getData());
        }

        questionDao.saveAll(questionsToSave);

        return new SuccessResult(QuestionMessages.questionsAdded);

    }

    @Override
    public DataResult<List<Question>> getQuestionsByFormId(int formId) {
        var questions = questionDao.findAllByFormId(formId);

        if(questions.isEmpty()){
            return new ErrorDataResult<>(QuestionMessages.questionsNotFound);
        }

        return new SuccessDataResult<>(questions.get(), QuestionMessages.questionsSuccessfullyBrought);
    }

    private boolean CheckIfQuestionTypeValid(String questionType){
        return questionType.equals(QuestionType.TYPE_RATING.getValue()) || questionType.equals(QuestionType.TYPE_TEXT.getValue()) || questionType.equals(QuestionType.TYPE_DATE.getValue());
    }

    private DataResult<Question> createQuestionFromDto(CreateQuestionDto createQuestionDto, Form form, User user){
        Question question;
        if (createQuestionDto.getQuestionType().equals(QuestionType.TYPE_RATING.getValue())){
            if (createQuestionDto.getMaxRating() < 1 || createQuestionDto.getMaxRating() > 10){
                return new ErrorDataResult<>(QuestionMessages.questionMaxRatingShouldBeBetween1And10);
            }
            question = new QuestionRating(createQuestionDto.getMaxRating());
        } else  if (createQuestionDto.getQuestionType().equals(QuestionType.TYPE_TEXT.getValue())){
            question = new QuestionText(createQuestionDto.getQuestionText());
        } else if (createQuestionDto.getQuestionType().equals(QuestionType.TYPE_DATE.getValue())){
            question = new QuestionDate(createQuestionDto.getQuestionText());
        } else {
            return new ErrorDataResult<>(QuestionMessages.questionTypeNotValid);
        }

        question.setQuestionText(createQuestionDto.getQuestionText());
        question.setQuestionOrder(createQuestionDto.getQuestionOrder());
        question.setRequired(createQuestionDto.isRequired());
        question.setForm(form);
        question.setCreator(user);
        return new SuccessDataResult<>(question, QuestionMessages.questionCreated);
    }
}
