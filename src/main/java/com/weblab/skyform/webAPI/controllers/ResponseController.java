package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.ResponseService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.dtos.response.CreateResponseDto;
import com.weblab.skyform.entities.dtos.response.GetResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    private final ResponseService responseService;

    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping("/addResponse")
    public ResponseEntity<Result> addResponse(@RequestBody CreateResponseDto createResponseDto){
        var result = responseService.addResponse(createResponseDto);

        if(!result.isSuccess()){

            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllResponses")
    public ResponseEntity<DataResult<List<GetResponseDto>>> getAllResponses(){
        var result = responseService.getAllResponsesDto();

        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getResponseById/{responseId}")
    public ResponseEntity<DataResult<GetResponseDto>> getResponseById(@PathVariable int responseId){
        var result = responseService.getResponseDtoById(responseId);

        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/addResponses/{formId}")
    public ResponseEntity<Result> addResponses(@RequestBody List<CreateResponseDto> createResponseDtos, @PathVariable int formId){
        var result = responseService.addResponses(createResponseDtos, formId);

        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getResponsesByResponseSession/{responseSession}")
    public ResponseEntity<DataResult<List<GetResponseDto>>> getResponsesByResponseSession(@PathVariable String responseSession){
        var result = responseService.getResponsesByResponseSession(responseSession);

        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/exportFormResponsesToExcelByFormId/{formId}")
    public ResponseEntity<byte[]> exportFormResponsesToExcelByFormId(@PathVariable int formId){
        var result = responseService.exportFormResponsesToExcelByFormId(formId);

        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result.getData());
        }

        return ResponseEntity.ok(result.getData());
    }



}
