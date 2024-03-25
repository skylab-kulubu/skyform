package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion")
    public Result addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("/getQuestionById")
    public Result getQuestionById(@RequestParam int id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("/getQuestionsByFormId")
    public Result getQuestionsByFormId(@RequestParam int formId){
        return questionService.getQuestionsByFormId(formId);
    }

    @GetMapping("/getQuestions")
    public Result getQuestions(){
        return questionService.getQuestions();
    }



}
