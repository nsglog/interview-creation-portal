package com.scaler.assignment.services;

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

    public Optional<Interview> createInterview (Interview interview) {
        return null;
    }

    public Optional<Interview> updateInterview (Interview interview) {
        return null;
    }

    public Optional<Interview> deleteInterview (Interview interview) {
        return null;
    }
}
