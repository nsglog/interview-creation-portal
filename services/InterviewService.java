package com.scaler.assignment.services;

import com.scaler.assignment.models.Interview;
import com.scaler.assignment.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {

    private InterviewRepository interviewRepository;

    @Autowired
    public InterviewService (InterviewRepository interviewRepository)   {
        this.interviewRepository = interviewRepository;
    }

    public Interview createInterview () {
        return null;
    }

    public Interview updateInterview () {
        return null;
    }

    public Interview deleteInterview () {
        return null;
    }
}
