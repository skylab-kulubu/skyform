package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.QuestionOption;
import com.weblab.skyform.entities.dtos.QuestionOptionDto;

import java.util.List;

public interface QuestionOptionService {

    Result addQuestionOption(QuestionOptionDto questionOptionDto);

    DataResult<QuestionOption> getQuestionOptionById(int id);

    DataResult<List<QuestionOption>> getAllQuestionOptions();

    DataResult<List<QuestionOption>> getQuestionOptionsByQuestionId(int questionId);


}
