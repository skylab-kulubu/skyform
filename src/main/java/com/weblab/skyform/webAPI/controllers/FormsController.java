package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.FormService;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.Form;
import com.weblab.skyform.entities.dtos.FormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/forms")
public class FormsController {

    private FormService formService;

    @Autowired
    FormsController(FormService formService){
        this.formService = formService;
    }

    @PostMapping("/addForm")
    public Result addForm(@RequestBody FormDto formDto){
        return formService.addForm(formDto);
    }

    @GetMapping("/getForms")
    public DataResult<List<Form>> getForms(){
        return formService.getForms();
    }

    @GetMapping("/getForm")
    public DataResult<Form> getForm(@RequestParam int formId){
        return formService.getFormById(formId);
    }

    @DeleteMapping("/deleteForm")
    public Result deleteForm(@RequestParam int formId){
        return formService.deleteForm(formId);
    }




}
