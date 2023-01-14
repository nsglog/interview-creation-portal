package com.scaler.assignment.controller;

import com.scaler.assignment.models.User;
import com.scaler.assignment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public HttpStatus addUser (@RequestBody User user)  {
        userService.addUser(user);
        return HttpStatus.OK;
    }
}
