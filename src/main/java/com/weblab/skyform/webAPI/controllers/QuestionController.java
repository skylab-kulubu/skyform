package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.core.utilities.results.Result;

import com.weblab.skyform.entities.Question;
import com.weblab.skyform.entities.dtos.question.CreateQuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

   @PostMapping("/addQuestion")
    public ResponseEntity<Result> addQuestion(@RequestBody CreateQuestionDto createQuestionDto) {
        var result = questionService.addQuestion(createQuestionDto);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getQuestionById/{id}")
    public ResponseEntity<Result> getQuestionById(@PathVariable int id) {
        var result = questionService.getQuestionById(id);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<Result> getAllQuestions() {
        var result = questionService.getAllQuestions();

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }


}
