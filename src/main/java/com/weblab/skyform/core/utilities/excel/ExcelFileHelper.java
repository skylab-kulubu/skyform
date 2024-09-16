package com.weblab.skyform.core.utilities.excel;

import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.business.abstracts.ResponseService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.ErrorDataResult;
import com.weblab.skyform.entities.Form;
import org.springframework.stereotype.Service;

@Service
public class ExcelFileHelper {

    private final UserService userService;

    private final FormService formService;

    private final QuestionService questionService;

    private final ResponseService responseService;

    public ExcelFileHelper(FormService formService, UserService userService, QuestionService questionService, ResponseService responseService) {
        this.formService = formService;
        this.userService = userService;
        this.questionService = questionService;
        this.responseService = responseService;
    }


    public DataResult<byte[]> exportFormResponsesToExcel(int formId) {

        var formResult = formService.getFormById(formId);
        if (!formResult.isSuccess()) {
            return new ErrorDataResult<>(null, formResult.getMessage());
        }
        var form = formResult.getData();

        var excelFile = createExcelFile(form);
    return null;

    }

    private Object createExcelFile(Form form) {
        return null;
    }
}
