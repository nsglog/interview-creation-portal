package com.scaler.assignment.controller;

import com.scaler.assignment.dtos.requestdtos.CreateInterviewRequestDto;
import com.scaler.assignment.dtos.requestdtos.DeleteInterviewRequestDto;
import com.scaler.assignment.dtos.requestdtos.UpdateInterviewRequestDto;
import com.scaler.assignment.dtos.responsedtos.CreateInterviewResponseDto;
import com.scaler.assignment.dtos.responsedtos.DeleteInterviewResponseDto;
import com.scaler.assignment.dtos.responsedtos.UpdateInterviewResponseDto;
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
    public @ResponseBody CreateInterviewResponseDto createInterview (@RequestBody CreateInterviewRequestDto requestDto) {

        Interview upcomingInterview;
        CreateInterviewResponseDto createInterviewResponseDto = new CreateInterviewResponseDto();

        try {
            upcomingInterview = interviewService.createInterview(requestDto);
            createInterviewResponseDto.setInterview(upcomingInterview);
            createInterviewResponseDto.setMessage("Interview Successfully created");
            createInterviewResponseDto.setHttpStatus(HttpStatus.CREATED);
        }
        catch (Exception exception) {
            createInterviewResponseDto.setMessage(exception.getMessage());
            createInterviewResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        }

        return createInterviewResponseDto;
    }


    @PutMapping(value = "/interview/{id}")
    public @ResponseBody UpdateInterviewResponseDto updateInterview (@RequestBody UpdateInterviewRequestDto requestDto, @PathVariable String id) {

        UpdateInterviewResponseDto updateInterviewResponseDto = new UpdateInterviewResponseDto();
        Interview updatedInterview;

        try {
            updatedInterview = interviewService.updateInterview(requestDto, Long.parseLong(id));
            updateInterviewResponseDto.setInterview(updatedInterview);
            updateInterviewResponseDto.setMessage("Interview Update Successfully");
            updateInterviewResponseDto.setHttpStatus(HttpStatus.OK);
        }
        catch (Exception exception) {
            updateInterviewResponseDto.setMessage(exception.getMessage());
            updateInterviewResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        }

        return updateInterviewResponseDto;
    }

    @DeleteMapping(value = "/interview/{id}")
    public @ResponseBody DeleteInterviewResponseDto deleteInterview (@RequestBody DeleteInterviewRequestDto requestDto, @PathVariable String id) {
        Interview deletedInterview = interviewService.deleteInterview(requestDto, Long.parseLong(id));
        return null;
    }
}
