package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Question;

import java.util.List;

public interface QuestionService {


    Result addQuestion(Question question);

    DataResult<Question> getQuestionById(int id);

    DataResult<List<Question>> getQuestionsByFormId(int formId);

    DataResult<List<Question>> getQuestions();

}
