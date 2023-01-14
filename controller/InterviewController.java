package com.scaler.assignment.controller;

import com.scaler.assignment.models.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class InterviewController {

    private InterviewService interviewService;
    @Autowired
    public InterviewController(InterviewService interviewService)   {
        this.interviewService = interviewService;
    }

//    CreateInterviewRequestDto createInterviewRequestDto = new CreateInterviewRequestDto();

    @PostMapping
    public HttpResponse createInterview () {
        return interviewService.createInterview(.....);
    }

    @PutMapping
    public HttpResponse updateInterview () {
        return interviewService.updateInterview(...);
    }

    @DeleteMapping
    public HttpResponse deleteInterview () {

    }
}
