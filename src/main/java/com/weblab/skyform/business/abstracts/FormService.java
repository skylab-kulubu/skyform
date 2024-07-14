package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Form;
import com.weblab.skyform.entities.dtos.form.CreateFormDto;
import com.weblab.skyform.entities.dtos.form.GetFormDto;

import java.util.List;

public interface FormService {

    Result addForm(CreateFormDto formDto);

    DataResult<List<Form>> getAllForms();

    DataResult<List<GetFormDto>> getAllFormsDto();

    DataResult<Form> getFormById(int formId);

    DataResult<GetFormDto> getFormDtoById(int formId);


}
