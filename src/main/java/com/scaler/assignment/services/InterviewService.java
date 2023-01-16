package com.scaler.assignment.services;

import com.scaler.assignment.dtos.CreateInterviewRequestDto;
import com.scaler.assignment.dtos.DeleteInterviewRequestDto;
import com.scaler.assignment.dtos.UpdateInterviewRequestDto;
import com.scaler.assignment.models.Interview;
import com.scaler.assignment.models.User;
import com.scaler.assignment.repository.InterviewRepository;
import com.scaler.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class InterviewService {
    private InterviewRepository interviewRepository;
    private UserRepository userRepository;

    @Autowired
    public InterviewService (InterviewRepository interviewRepository, UserRepository userRepository)   {
        this.interviewRepository = interviewRepository;
        this.userRepository = userRepository;
    }

    public Interview createInterview (CreateInterviewRequestDto requestDto) {

        Optional<User> userRequesting = userRepository.findById(requestDto.getRequestedById());
        Optional<User> userRequested = userRepository.findById(requestDto.getRequestedToId());

        User requestingUser = userRequesting.get();
        User requestedUser = userRequested.get();

        List<Interview> requestingUserInterviews =
                interviewRepository.findByUser(requestingUser.getId(), requestDto.getStartTime(), requestDto.getEndTime());
        List<Interview> requestedUserInterviews =
                interviewRepository.findByUser(requestedUser.getId(), requestDto.getStartTime(), requestDto.getEndTime());


        if(requestedUserInterviews.isEmpty() && requestingUserInterviews.isEmpty()) {
            Interview interview = setInterview (requestDto, requestingUser, requestedUser);
            return interview;
        }

        return null;
    }

    private Interview setInterview (CreateInterviewRequestDto requestDto, User requestedBy, User requestedWith)    {
        Interview interview = new Interview();
        interview.setStartTime(requestDto.getStartTime());
        interview.setEndTime(requestDto.getEndTime());
        interview.setBookedBy(requestedBy);
        interview.setBookedWith(requestedWith);
        interviewRepository.save(interview);
        return interview;
    }



    public Interview updateInterview (UpdateInterviewRequestDto requestDto, long id) {

        Optional<Interview> interview = interviewRepository.findById(id);

        Interview scheduledInterview = interview.get();

        Optional<User> user = userRepository.findById(requestDto.getBookWithId());

        User bookWithUser = user.get();
        User requestingUser = scheduledInterview.getBookedBy();

        LocalDateTime start = requestDto.getNewStartTime();
        LocalDateTime end = requestDto.getNewEndTime();

        List<Interview> scheduledInterviewsOfRequestingUser = interviewRepository.findByUser(bookWithUser.getId(),
                start,
                end,
                id);

        List<Interview> scheduledInterviewsOfRequestedUsers = interviewRepository.findByUser(requestingUser.getId(),
                start,
                end,
                id);

        if(scheduledInterviewsOfRequestedUsers.isEmpty() &&
            scheduledInterviewsOfRequestingUser.isEmpty())  {
            scheduledInterview.setStartTime(start);
            scheduledInterview.setEndTime(end);
            scheduledInterview.setBookedWith(bookWithUser);
            scheduledInterview.setBookedBy(requestingUser);

            return scheduledInterview;
        }

        return null;
    }

    public Interview deleteInterview (DeleteInterviewRequestDto requestDto, long id) {
        return null;
    }
}
