package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.DateQuestionResponseService;
import com.weblab.skyform.business.abstracts.OptionQuestionResponseService;
import com.weblab.skyform.business.abstracts.TextQuestionResponseService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.entities.DateQuestionResponse;
import com.weblab.skyform.entities.OptionQuestionResponse;
import com.weblab.skyform.entities.TextQuestionResponse;
import com.weblab.skyform.entities.dtos.DateQuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Text;

@RestController
@RequestMapping("api/questionresponses")
public class QuestionResponsesController {

    private DateQuestionResponseService dateQuestionResponseService;

    private TextQuestionResponseService textQuestionResponseService;

    private OptionQuestionResponseService optionQuestionResponseService;

    @Autowired
    public QuestionResponsesController(DateQuestionResponseService dateQuestionResponseService,
                                       TextQuestionResponseService textQuestionResponseService,
                                       OptionQuestionResponseService optionQuestionResponseService) {
        this.dateQuestionResponseService = dateQuestionResponseService;
        this.textQuestionResponseService = textQuestionResponseService;
        this.optionQuestionResponseService = optionQuestionResponseService;
    }

    @PostMapping("/adddatequestionresponse")
    public Result addDateQuestionResponse(DateQuestionResponseDto dateQuestionResponseDto){
        return dateQuestionResponseService.addDateQuestionResponse(dateQuestionResponseDto);
    }

    @PostMapping("/addtextquestionresponse")
    public Result addTextQuestionResponse(TextQuestionResponse textQuestionResponse){
        textQuestionResponseService.addTextQuestion(textQuestionResponse);

        return new SuccessResult(Messages.textQuestionResponseAddSuccess);
    }

    @PostMapping("/addoptionquestionresponse")
    public Result addOptionQuestionResponse(OptionQuestionResponse optionQuestionResponse){
        optionQuestionResponseService.addOptionQuestion(optionQuestionResponse);

        return new SuccessResult(Messages.optionQuestionResponseAddSuccess);
    }


}
