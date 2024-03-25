package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.ResponseService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping("/addResponse")
    public Result addResponse(@RequestBody Response response) {
        return responseService.addResponse(response);
    }

    @GetMapping("/getResponses")
    public DataResult<List<Response>> getResponses() {
        return responseService.getResponses();
    }


}
