package com.weblab.skyform.webAPI.controllers;


import com.weblab.skyform.business.abstracts.UserService;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.User;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<Result> addUser(@RequestBody CreateUserDto createUserDto) {
        var result = userService.addUser(createUserDto);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<DataResult<List<GetUserDto>>> getAllUsers() {
        var result = userService.getAllUsersDto();

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<DataResult<GetUserDto>> getUserById(@PathVariable int id) {
        var result = userService.getUserDtoById(id);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

            return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<DataResult<GetUserDto>> getUserByEmail(@PathVariable String email) {
        var result = userService.getUserDtoByEmail(email);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Result> updateUser(@RequestBody User user, @PathVariable int id) {
        user.setId(id);
        var result = userService.updateUser(user);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }







}