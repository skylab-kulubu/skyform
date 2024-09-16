package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.dtos.form.CreateFormDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping("/addForm")
    public ResponseEntity<Result> addForm(@RequestBody CreateFormDto createFormDto) {
        var result = formService.addForm(createFormDto);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

   @GetMapping("/getAllForms")
    public ResponseEntity<Result> getAllForms() {
        var result = formService.getAllFormsDto();

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getFormById/{id}")
    public ResponseEntity<Result> getFormById(@PathVariable int id) {
        var result = formService.getFormDtoById(id);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

}
