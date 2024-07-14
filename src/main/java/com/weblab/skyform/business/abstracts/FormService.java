package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.dtos.form.CreateFormDto;

public interface FormService {

    Result addForm(CreateFormDto formDto);


}
