package com.scaler.assignment.controller;

import com.scaler.assignment.models.User;
import com.scaler.assignment.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public HttpResponse addUser (@RequestBody User user) {
        Optional<User> = userService.addUser(user);
    }
}
