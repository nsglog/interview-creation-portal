package com.scaler.assignment.controller;

import com.scaler.assignment.models.Interview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.http.HttpResponse;

@Controller
public class InterviewController {

    private InterviewService interviewService;
    @Autowired
    public InterviewController(InterviewService interviewService)   {
        this.interviewService = interviewService;
    }

    CreateInterviewRequestDto createInterviewRequestDto = new CreateInterviewRequestDto();

    public HttpResponse createInterview () {
        return interviewService.createInterview(.....);
    }

    public HttpResponse updateInterview () {
        return interviewService.updateInterview(...);
    }

    public HttpResponse deleteInterview () {
                

    }
}
