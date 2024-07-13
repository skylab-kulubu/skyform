package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.AuthService;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public DataResult<String> login(String email, String password, HttpServletResponse response) {
        return authService.login(email, password, response);
    }

    @PostMapping("/register")
    public Result register(CreateUserDto createUserDto){
        return authService.register(createUserDto);
    }


}
