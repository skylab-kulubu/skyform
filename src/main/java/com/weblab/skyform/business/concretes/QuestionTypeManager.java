package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.QuestionTypeService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessDataResult;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.QuestionTypeDao;
import com.weblab.skyform.entities.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeManager implements QuestionTypeService {

    private QuestionTypeDao questionTypeDao;

    @Autowired
    public QuestionTypeManager(QuestionTypeDao questionTypeDao) {
        this.questionTypeDao = questionTypeDao;
    }

    @Override
    public Result addQuestionType(QuestionType questionType) {
        questionTypeDao.save(questionType);
        return new SuccessResult(Messages.addQuestionTypeSuccess);
    }

    @Override
    public DataResult<List<QuestionType>> getQuestionTypes() {
        var result = questionTypeDao.findAll();
        return new SuccessDataResult<List<QuestionType>>(result, Messages.getQuestionTypesSuccess);
    }

    @Override
    public DataResult<QuestionType> getQuestionTypeById(int id) {
        var result = questionTypeDao.getQuestionTypeById(id);
        return new SuccessDataResult<QuestionType>(result, Messages.getQuestionTypeByIdSuccess);
    }
}
