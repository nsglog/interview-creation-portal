package com.scaler.assignment.controller;

import com.scaler.assignment.models.Interview;
import com.scaler.assignment.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class InterviewController {

    private InterviewService interviewService;
    @Autowired
    public InterviewController (@RequestBody InterviewService interviewService)   {
        this.interviewService = interviewService;
    }

    @PostMapping
    public HttpStatus createInterview (@RequestBody Interview interview) {
        Optional<Interview> upcomingInterview = interviewService.createInterview(interview);
        return HttpStatus.CREATED;
    }


    @PutMapping
    public HttpStatus updateInterview (@RequestBody Interview interview) {
        Optional <Interview> updatedInterview = interviewService.updateInterview(interview);
        return HttpStatus.OK;
    }

    @DeleteMapping
    public HttpStatus deleteInterview (@RequestBody Interview interview) {
        Optional <Interview> deletedInterview = interviewService.deleteInterview(interview);
        return HttpStatus.OK;
    }
}
