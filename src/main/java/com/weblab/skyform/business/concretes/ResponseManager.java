package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.ResponseService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.core.utilities.results.SuccessDataResult;
import com.weblab.skyform.core.utilities.results.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.ResponseDao;
import com.weblab.skyform.entities.Response;
import com.weblab.skyform.entities.ResponseDateQuestion;
import com.weblab.skyform.entities.ResponseOptionQuestion;
import com.weblab.skyform.entities.ResponseTextQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseManager implements ResponseService {

    @Autowired
    private ResponseDao responseDao;


    @Override
    public Result addResponse(Response response) {
        responseDao.save(response);

        return new SuccessResult("Response is added.");
    }

    @Override
    public DataResult<List<Response>> getResponses() {
        var result = responseDao.findAll();

        return new SuccessDataResult<List<Response>>(result, "Responses are listed.");
    }
}
