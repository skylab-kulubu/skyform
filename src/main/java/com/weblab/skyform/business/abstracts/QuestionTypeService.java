package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.QuestionType;

import java.util.List;

public interface QuestionTypeService {
    Result addQuestionType(QuestionType questionType);

    DataResult<List<QuestionType>> getQuestionTypes();

    DataResult<QuestionType> getQuestionTypeById(int id);
}
