package com.scaler.assignment.controller;

import com.scaler.assignment.dtos.CreateInterviewRequestDto;
import com.scaler.assignment.dtos.DeleteInterviewRequestDto;
import com.scaler.assignment.dtos.UpdateInterviewRequestDto;
import com.scaler.assignment.models.Interview;
import com.scaler.assignment.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class InterviewController {

    private InterviewService interviewService;
    @Autowired
    public InterviewController (InterviewService interviewService)   {
        this.interviewService = interviewService;
    }

    @PostMapping(value = "/interview")
    public HttpStatus createInterview (@RequestBody CreateInterviewRequestDto requestDto) {
        Interview upcomingInterview = interviewService.createInterview(requestDto);
        return HttpStatus.CREATED;
    }


    @PutMapping(value = "/interview/{id}")
    public HttpStatus updateInterview (@RequestBody UpdateInterviewRequestDto requestDto, @PathVariable String id) {
        Interview interview = interviewService.updateInterview(requestDto, Long.parseLong(id));
        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/interview/{id}")
    public HttpStatus deleteInterview (@RequestBody DeleteInterviewRequestDto requestDto, @PathVariable String id) {
        Interview deletedInterview = interviewService.deleteInterview(requestDto, Long.parseLong(id));
        return HttpStatus.OK;
    }
}
