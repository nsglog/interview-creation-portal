package com.scaler.assignment.controller;

import com.scaler.assignment.dtos.CreateInterviewRequestDto;
import com.scaler.assignment.dtos.DeleteInterviewRequestDto;
import com.scaler.assignment.dtos.UpdateRequestDto.UpdateInterviewRequestDto;
import com.scaler.assignment.dtos.UpdateRequestDto.UpdateRequestByParticipantDto;
import com.scaler.assignment.dtos.UpdateRequestDto.UpdateRequestByTimeDto;
import com.scaler.assignment.models.Interview;
import com.scaler.assignment.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class InterviewController {

    private InterviewService interviewService;
    @Autowired
    public InterviewController (@RequestBody InterviewService interviewService)   {
        this.interviewService = interviewService;
    }

    @PostMapping(value = "/interview")
    public HttpStatus createInterview (@RequestBody CreateInterviewRequestDto requestDto) {
        Optional<Interview> upcomingInterview = interviewService.createInterview(requestDto);
        return HttpStatus.CREATED;
    }


    @PutMapping(value = "/interview/email")
    public HttpStatus updateInterviewByParticipant (@RequestBody UpdateRequestByParticipantDto requestDto) {
        Optional <Interview> updatedInterviewByParticipant = interviewService.updateInterviewByParticipant(requestDto);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/interview/time")
    public HttpStatus updateInterviewByTime (@RequestBody UpdateRequestByTimeDto requestDto) {
        Optional <Interview> updatedInterviewByTime = interviewService.updateInterviewByTime(requestDto);
        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/interview")
    public HttpStatus deleteInterview (@RequestBody DeleteInterviewRequestDto requestDto) {
        Optional <Interview> deletedInterview = interviewService.deleteInterview(requestDto);
        return HttpStatus.OK;
    }
}
