package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.QuestionOptionService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessDataResult;
import com.weblab.skyform.entities.QuestionOption;
import com.weblab.skyform.entities.dtos.QuestionOptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questionoptions")
public class QuestionOptionsController {

    private QuestionOptionService questionOptionService;

    @Autowired
    public QuestionOptionsController(QuestionOptionService questionOptionService) {
        this.questionOptionService = questionOptionService;
    }

    @PostMapping("/addquestionoption")
    public Result addQuestionOption(@RequestBody QuestionOptionDto questionOptionDto){
        return questionOptionService.addQuestionOption(questionOptionDto);
    }

    @GetMapping("/getquestionoptionbyid")
    public DataResult<QuestionOption> getQuestionOptionById(@RequestParam int id){
        return questionOptionService.getQuestionOptionById(id);
    }

    @GetMapping("/getallquestionoptions")
    public DataResult<List<QuestionOption>> getAllQuestionOptions() {
        return questionOptionService.getAllQuestionOptions();
    }

    @GetMapping("/getquestionoptionsbyquestionid")
    public DataResult<List<QuestionOption>> getQuestionOptionsByQuestionId(@RequestParam int questionId) {
        return questionOptionService.getQuestionOptionsByQuestionId(questionId);
    }

}
