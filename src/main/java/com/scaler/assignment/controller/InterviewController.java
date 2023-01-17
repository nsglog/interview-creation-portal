package com.scaler.assignment.controller;

import com.scaler.assignment.dtos.requestdtos.CreateInterviewRequestDto;
import com.scaler.assignment.dtos.requestdtos.DeleteInterviewRequestDto;
import com.scaler.assignment.dtos.requestdtos.UpdateInterviewRequestDto;
import com.scaler.assignment.dtos.responsedtos.CreateInterviewResponseDto;
import com.scaler.assignment.dtos.responsedtos.DeleteInterviewResponseDto;
import com.scaler.assignment.dtos.responsedtos.GetListOfInterviewResponseDto;
import com.scaler.assignment.dtos.responsedtos.UpdateInterviewResponseDto;
import com.scaler.assignment.models.Interview;
import com.scaler.assignment.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class InterviewController {

    private InterviewService interviewService;
    @Autowired
    public InterviewController (InterviewService interviewService)   {
        this.interviewService = interviewService;
    }

    @GetMapping(value = "/interview")
    public @ResponseBody GetListOfInterviewResponseDto getListOfInterview () {
        List<Interview> upcomingInterviews = interviewService.getListOfInterview();
        GetListOfInterviewResponseDto getListOfInterviewResponseDto = new GetListOfInterviewResponseDto();
        getListOfInterviewResponseDto.setInterviewList(upcomingInterviews);
        return getListOfInterviewResponseDto;
    }

    @PostMapping(value = "/interview")
    public @ResponseBody CreateInterviewResponseDto createInterview (@RequestBody CreateInterviewRequestDto requestDto) {

        System.out.println(requestDto.getRequestedById());
        System.out.println(requestDto.getRequestedToId());
        System.out.println(requestDto.getStartTime());
        System.out.println(requestDto.getEndTime());
        Interview upcomingInterview;
        CreateInterviewResponseDto createInterviewResponseDto = new CreateInterviewResponseDto();

        try {
            upcomingInterview = interviewService.createInterview(requestDto);
            createInterviewResponseDto.setInterview(upcomingInterview);
        }
        catch (Exception exception) {
            createInterviewResponseDto.setMessage(exception.getMessage());
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
        }
        catch (Exception exception) {
            updateInterviewResponseDto.setMessage(exception.getMessage());
        }

        return updateInterviewResponseDto;
    }

    @DeleteMapping(value = "/interview/{id}")
    public @ResponseBody DeleteInterviewResponseDto deleteInterview (@RequestBody DeleteInterviewRequestDto requestDto, @PathVariable String id) {
        Interview deletedInterview = interviewService.deleteInterview(requestDto, Long.parseLong(id));
        return null;
    }
}
