package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/getUsers")
    public DataResult<List<User>> getUsers(){
        return userService.getUsers();
    }

}
