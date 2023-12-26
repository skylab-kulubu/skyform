package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.TextQuestionResponseService;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.TextQuestionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextQuestionResponseManager implements TextQuestionResponseService {
    @Override
    public Result addTextQuestion(TextQuestionResponse textQuestionResponse) {
        return null;
    }

    @Override
    public DataResult<List<TextQuestionResponse>> getAllTextQuestionResponses() {
        return null;
    }

    @Override
    public DataResult<TextQuestionResponse> getTextQuestionResponseById(int id) {
        return null;
    }
}

