package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.constants.QuestionMessages;
import com.weblab.skyform.core.utilities.results.*;
import com.weblab.skyform.dataAccess.abstracts.QuestionDao;
import com.weblab.skyform.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionManager implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public Result addQuestion(Question question) {
        questionDao.save(question);
        return new SuccessResult(QuestionMessages.questionAdded);
    }

    @Override
    public DataResult<Question> getQuestionById(int id) {
        var result = questionDao.findById(id);
        if (!result.isPresent()){
            return new ErrorDataResult<Question>(QuestionMessages.questionGetByIdError);
        }
        return new SuccessDataResult<Question>(result.get(), QuestionMessages.questionGetByIdSuccess);
    }

    @Override
    public DataResult<List<Question>> getQuestionsByFormId(int formId) {
        return null;
    }

    @Override
    public DataResult<List<Question>> getQuestions() {
        var result = questionDao.findAll();

        return new SuccessDataResult<List<Question>>(result, QuestionMessages.questionGetAllSuccess);
    }
}
