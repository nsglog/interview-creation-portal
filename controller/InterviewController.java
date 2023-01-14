package com.scaler.assignment.controller;

import com.scaler.assignment.models.Interview;
import com.scaler.assignment.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Optional;

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
        Optional <Interview> interview = interviewService.createInterview(...);
    }

    @PutMapping
    public HttpResponse updateInterview () {
        Optional <Interview> interview = interviewService.updateInterview(...);
    }

    @DeleteMapping
    public HttpResponse deleteInterview () {
        Optional <Interview> interview = interviewService.deleteInterview(...);
    }
}
