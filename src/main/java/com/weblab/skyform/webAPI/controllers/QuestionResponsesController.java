package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.DateQuestionResponseService;
import com.weblab.skyform.business.abstracts.OptionQuestionResponseService;
import com.weblab.skyform.business.abstracts.TextQuestionResponseService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.entities.DateQuestionResponse;
import com.weblab.skyform.entities.OptionQuestionResponse;
import com.weblab.skyform.entities.TextQuestionResponse;
import com.weblab.skyform.entities.dtos.DateQuestionResponseDto;
import com.weblab.skyform.entities.dtos.OptionQuestionResponseDto;
import com.weblab.skyform.entities.dtos.TextQuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Text;

import java.util.List;

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

    @PostMapping("/addDateQuestionResponse")
    public Result addDateQuestionResponse(@RequestBody DateQuestionResponseDto dateQuestionResponseDto){
        return dateQuestionResponseService.addDateQuestionResponse(dateQuestionResponseDto);
    }

    @PostMapping("/addTextQuestionResponse")
    public Result addTextQuestionResponse(@RequestBody TextQuestionResponseDto textQuestionResponseDto){
        textQuestionResponseService.addTextQuestion(textQuestionResponseDto);

        return new SuccessResult(Messages.textQuestionResponseAddSuccess);
    }

    @PostMapping("/addOptionQuestionResponse")
    public Result addOptionQuestionResponse(@RequestBody OptionQuestionResponseDto optionQuestionResponseDto){
        optionQuestionResponseService.addOptionQuestion(optionQuestionResponseDto);

        return new SuccessResult(Messages.optionQuestionResponseAddSuccess);
    }

    @GetMapping("/getTextQuestionResponses")
    public DataResult<List<TextQuestionResponse>> getTextQuestionResponses(){
        return textQuestionResponseService.getAllTextQuestionResponses();
    }

    @GetMapping("/getDateQuestionResponses")
    public DataResult<List<DateQuestionResponse>> getDateQuestionResponses(){
        return dateQuestionResponseService.getAllDateQuestionResponses();
    }

    @GetMapping("/getOptionQuestionResponses")
    public DataResult<List<OptionQuestionResponse>> getOptionQuestionResponses(){
        return optionQuestionResponseService.getAllOptionQuestionResponses();
    }


}
