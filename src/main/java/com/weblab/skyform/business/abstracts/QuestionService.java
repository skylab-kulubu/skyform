package com.weblab.skyform.business.abstracts;


import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Question;
import com.weblab.skyform.entities.dtos.question.CreateQuestionDto;

import java.util.List;

public interface QuestionService {

    Result addQuestion(CreateQuestionDto createQuestionDto);

    DataResult<Question> getQuestionById(int id);

    DataResult<List<Question>> getAllQuestions();


}
