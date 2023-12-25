package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.QuestionTypeService;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questiontypes")
public class QuestionTypesController {

    private QuestionTypeService questionTypeService;

    @Autowired
    public QuestionTypesController(QuestionTypeService questionTypeService) {
        this.questionTypeService = questionTypeService;
    }


    @PostMapping("/addquestiontype")
    public Result addQuestionType(@RequestBody QuestionType questionType){
        return questionTypeService.addQuestionType(questionType);
    }

    @GetMapping("/getquestiontypes")
    public DataResult<List<QuestionType>> getQuestionTypes(){
        return questionTypeService.getQuestionTypes();
    }



}
