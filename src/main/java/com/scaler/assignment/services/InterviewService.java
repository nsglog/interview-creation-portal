package com.scaler.assignment.services;

import com.scaler.assignment.dtos.CreateInterviewRequestDto;
import com.scaler.assignment.dtos.DeleteInterviewRequestDto;
import com.scaler.assignment.dtos.UpdateRequestDto.UpdateRequestByParticipantDto;
import com.scaler.assignment.dtos.UpdateRequestDto.UpdateRequestByTimeDto;
import com.scaler.assignment.models.Interview;
import com.scaler.assignment.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterviewService {

    private InterviewRepository interviewRepository;

    @Autowired
    public InterviewService (InterviewRepository interviewRepository)   {
        this.interviewRepository = interviewRepository;
    }

    public Optional<Interview> createInterview (CreateInterviewRequestDto requestDto) {

        return null;
    }

    public Optional<Interview> updateInterviewByTime (UpdateRequestByTimeDto requestDto) {

        return null;
    }

    public Optional<Interview> updateInterviewByParticipant (UpdateRequestByParticipantDto requestDto) {

        return null;
    }

    public Optional<Interview> deleteInterview (DeleteInterviewRequestDto requestDto) {
        return null;
    }
}
