package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.QuestionService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.dtos.question.CreateQuestionDto;
import com.weblab.skyform.entities.dtos.question.GetQuestionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/addQuestion/{formId}")
    public ResponseEntity<Result> addQuestion(@RequestBody CreateQuestionDto createQuestionDto, @PathVariable int formId) {
        var result = questionService.addQuestion(createQuestionDto, formId);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getQuestionById/{id}")
    public ResponseEntity<DataResult<GetQuestionDto>> getQuestionById(@PathVariable int id) {
        var result = questionService.getQuestionDtoById(id);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<Result> getAllQuestions() {
        var result = questionService.getAllQuestionsDto();

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/addQuestions/{formId}")
    public ResponseEntity<Result> addQuestions(@RequestBody List<CreateQuestionDto> createQuestionDtos, @PathVariable int formId) {

        var result = questionService.addQuestions(createQuestionDtos, formId);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}