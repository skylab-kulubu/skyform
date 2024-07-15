package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Response;
import com.weblab.skyform.entities.dtos.response.CreateResponseDto;
import com.weblab.skyform.entities.dtos.response.GetResponseDto;

import java.util.List;

public interface ResponseService {

    Result addResponse(CreateResponseDto createResponseDto);

    DataResult<List<Response>> getAllResponses();

    DataResult<List<GetResponseDto>> getAllResponsesDto();

    DataResult<Response> getResponseById(int id);

    DataResult<GetResponseDto> getResponseDtoById(int id);

}
