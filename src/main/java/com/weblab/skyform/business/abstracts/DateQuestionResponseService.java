package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.DateQuestionResponse;
import com.weblab.skyform.entities.OptionQuestionResponse;
import com.weblab.skyform.entities.dtos.DateQuestionResponseDto;

import java.util.List;

public interface DateQuestionResponseService {

    Result addDateQuestionResponse(DateQuestionResponseDto dateQuestionResponseDto);

    DataResult<List<OptionQuestionResponse>> getAllDateQuestionResponses();

    DataResult<OptionQuestionResponse> getDateQuestionResponseById(int id);

}

