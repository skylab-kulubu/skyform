package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.OptionQuestionResponse;

import java.util.List;

public interface OptionQuestionResponseService {

    Result addOptionQuestion(OptionQuestionResponse optionQuestionResponse);

    DataResult<List<OptionQuestionResponse>> getAllOptionQuestionResponses();

    DataResult<OptionQuestionResponse> getOptionQuestionResponseById(int id);

}
