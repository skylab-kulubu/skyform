package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.OptionQuestionResponseService;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.OptionQuestionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionQuestionResponseManager implements OptionQuestionResponseService {
    @Override
    public Result addOptionQuestion(OptionQuestionResponse optionQuestionResponse) {
        return null;
    }

    @Override
    public DataResult<List<OptionQuestionResponse>> getAllOptionQuestionResponses() {
        return null;
    }

    @Override
    public DataResult<OptionQuestionResponse> getOptionQuestionResponseById(int id) {
        return null;
    }
}
