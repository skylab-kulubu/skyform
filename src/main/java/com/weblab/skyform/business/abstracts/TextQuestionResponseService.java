package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.TextQuestionResponse;
import com.weblab.skyform.entities.dtos.TextQuestionResponseDto;

import java.util.List;

public interface TextQuestionResponseService {

    Result addTextQuestion(TextQuestionResponseDto textQuestionResponseDto);

    DataResult<List<TextQuestionResponse>> getAllTextQuestionResponses();

    DataResult<TextQuestionResponse> getTextQuestionResponseById(int id);


}
