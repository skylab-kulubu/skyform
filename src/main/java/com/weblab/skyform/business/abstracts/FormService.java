package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.Form;

import java.util.List;

public interface FormService {

    Result addForm(Form form);

    DataResult<List<Form>> getForms();

    Result addEventToForm(int eventId, int formId);

    DataResult<Form> getFormById(int id);

    Result deleteForm(int formId);
}
