package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.dtos.FormDto;

public interface FormService {

    Result add(FormDto formDto);


}
