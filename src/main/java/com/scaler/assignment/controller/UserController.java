package com.scaler.assignment.controller;

import com.scaler.assignment.dtos.responsedtos.GetListOfInterviewResponseDto;
import com.scaler.assignment.dtos.responsedtos.GetListOfUserResponseDto;
import com.scaler.assignment.models.Interview;
import com.scaler.assignment.models.User;
import com.scaler.assignment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public HttpStatus addUser(@RequestBody User user) {
        userService.addUser(user);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/user")
    public @ResponseBody GetListOfUserResponseDto getListOfUser() {
        List<User> userList = userService.getListOfUser();
        GetListOfUserResponseDto getListOfUserResponseDto = new GetListOfUserResponseDto();
        getListOfUserResponseDto.setUserList(userList);
        return getListOfUserResponseDto;
    }
}
