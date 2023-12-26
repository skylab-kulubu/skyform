package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.Question;
import com.weblab.skyform.entities.dtos.QuestionDto;

import java.util.List;

public interface QuestionService {

    DataResult<List<Question>> getQuestions();

    DataResult<List<Question>> getQuestionsByFormId(int formId);

    DataResult<Question> getQuestionById(int id);

    Result addQuestion(QuestionDto questionDto);

}
