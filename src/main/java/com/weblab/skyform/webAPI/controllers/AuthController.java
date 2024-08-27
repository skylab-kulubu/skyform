package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.AuthService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
import com.weblab.skyform.entities.dtos.user.LoginUserDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<DataResult<String>> login(@RequestBody LoginUserDto loginUserDto, HttpServletResponse response) {
        var result = authService.login(loginUserDto, response);

        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(401).body(result);
    }

    @PostMapping("/register")
    public ResponseEntity<Result> register(@RequestBody CreateUserDto createUserDto){

        var result = authService.register(createUserDto);

        if (result.isSuccess()){
            return ResponseEntity.status(201).body(result);
        }

        return ResponseEntity.status(400).body(result);
    }


}
