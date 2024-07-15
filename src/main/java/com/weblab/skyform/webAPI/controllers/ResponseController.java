package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.ResponseService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Response;
import com.weblab.skyform.entities.ResponseRating;
import com.weblab.skyform.entities.dtos.response.CreateResponseDto;
import com.weblab.skyform.entities.dtos.response.GetResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

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



}
