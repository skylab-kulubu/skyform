package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Response;
import com.weblab.skyform.entities.ResponseDateQuestion;
import com.weblab.skyform.entities.ResponseOptionQuestion;
import com.weblab.skyform.entities.ResponseTextQuestion;

import java.util.List;

public interface ResponseService {

    Result addResponse(Response response);

    DataResult<List<Response>> getResponses();

}
