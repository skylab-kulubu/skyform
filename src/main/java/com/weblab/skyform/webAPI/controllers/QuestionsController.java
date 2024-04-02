package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.Question;
import com.weblab.skyform.entities.dtos.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questions")
public class QuestionsController {

    private QuestionService questionService;

    @Autowired
    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/getQuestions")
    public DataResult<List<Question>> getQuestions(){
        return questionService.getQuestions();
    };

    @GetMapping("/getQuestionsByFormId")
    public DataResult<List<Question>> getQuestionsByFormId(@RequestParam int formId){
        return questionService.getQuestionsByFormId(formId);
    }

    @GetMapping("/getQuestionById")
    public DataResult<Question> getQuestionById(@RequestParam int id){
        return questionService.getQuestionById(id);
    }

    @PostMapping("/addQuestion")
    public Result addQuestion(@RequestBody QuestionDto questionDto){
        return questionService.addQuestion(questionDto);
    }

}
