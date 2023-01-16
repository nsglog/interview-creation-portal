package com.scaler.assignment.services;

import com.scaler.assignment.dtos.requestdtos.CreateInterviewRequestDto;
import com.scaler.assignment.dtos.requestdtos.DeleteInterviewRequestDto;
import com.scaler.assignment.dtos.requestdtos.UpdateInterviewRequestDto;
import com.scaler.assignment.models.Interview;
import com.scaler.assignment.models.User;
import com.scaler.assignment.repository.InterviewRepository;
import com.scaler.assignment.repository.UserRepository;
import exceptions.ConflictOfTimingException;
import exceptions.InterviewNotFoundException;
import exceptions.InvalidDatesException;
import exceptions.UserDoesNotExistException;
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

    public Interview createInterview (CreateInterviewRequestDto requestDto) throws Exception {

        System.out.println("debug 1+1+1");

        Long requestingUserId = requestDto.getRequestedById();
        Long requestedUserId = requestDto.getRequestedToId();

        System.out.println("first user id" + requestingUserId);
        System.out.println("second user id" + requestedUserId);

        Optional<User> userRequesting = userRepository.findById(requestingUserId);
        Optional<User> userRequested = userRepository.findById(requestedUserId);

        if(userRequesting.isEmpty() || userRequested.isEmpty()) {
            throw new UserDoesNotExistException("Invalid Id/ User does not exist");
        }

        System.out.println("debug 9+9+9");

        User requestingUser = userRequesting.get();
        User requestedUser = userRequested.get();

        LocalDateTime interviewStartTime = requestDto.getStartTime();
        LocalDateTime interviewEndTime = requestDto.getEndTime();

        if(interviewStartTime.compareTo(interviewEndTime) >= 0 ||
                interviewStartTime.compareTo(LocalDateTime.now()) < 0) {
            throw new InvalidDatesException ("Dates provided are invalid");
        }

        System.out.println("debug 10+10+10");

        List<Interview> interviewsBookedByRequestingUser = interviewRepository.findByBookedBy(requestingUser.getId(),
                interviewStartTime,
                interviewEndTime);
        List<Interview> interviewsBookedByRequestedUser = interviewRepository.findByBookedBy(requestedUser.getId(),
                interviewStartTime,
                interviewEndTime);
        List<Interview> interviewsBookedWithRequestingUser = interviewRepository.findByBookedWith(requestingUser.getId(),
                interviewStartTime,
                interviewEndTime);
        List<Interview> interviewsBookedWithRequestedUser = interviewRepository.findByBookedWith(requestedUser.getId(),
                interviewStartTime,
                interviewEndTime);

        System.out.println("debug 11+11+11");

//        System.out.println("size lists"+requestingUserInterviews.size()+" "+requestingUserInterviews.size());



        if(interviewsBookedByRequestingUser.isEmpty() && interviewsBookedByRequestedUser.isEmpty() &&
            interviewsBookedWithRequestingUser.isEmpty() && interviewsBookedWithRequestedUser.isEmpty()) {

            Interview interview = setInterview (interviewStartTime, interviewEndTime,
                                                requestingUser, requestedUser);

            return interview;
        }

        throw new ConflictOfTimingException("One of user has already scheduled an interview at requested time interval. ");
    }

    private Interview setInterview (LocalDateTime startTime, LocalDateTime endTime,
                                    User requestedBy, User requestedWith)    {
        Interview interview = new Interview();
        interview.setStartTime(startTime);
        interview.setEndTime(endTime);
        interview.setBookedBy(requestedBy);
        interview.setBookedWith(requestedWith);
        interviewRepository.save(interview);
        return interview;
    }



    public Interview updateInterview (UpdateInterviewRequestDto requestDto, long id) throws Exception {

        Optional<Interview> interview = interviewRepository.findById(id);

        if(interview.isEmpty()) {
            throw new InterviewNotFoundException("Requested interview does not exit");
        }

        Interview scheduledInterview = interview.get();

        Optional<User> user = userRepository.findById(requestDto.getBookWithId());

        if(user.isEmpty())  {
            throw new UserDoesNotExistException("Requested user does not exist");
        }

        User bookWithUser = user.get();
        User requestingUser = scheduledInterview.getBookedBy();

        LocalDateTime start = requestDto.getNewStartTime();
        LocalDateTime end = requestDto.getNewEndTime();

        if(start.compareTo(end) >= 0 ||
                start.compareTo(LocalDateTime.now()) < 0) {
            throw new InvalidDatesException ("Dates provided are invalid");
        }

        List<Interview> scheduledInterviewsOfRequestingUser = interviewRepository.findByParams(bookWithUser.getId(),
                start,
                end,
                id);

        List<Interview> scheduledInterviewsOfRequestedUsers = interviewRepository.findByParams(requestingUser.getId(),
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

        throw new ConflictOfTimingException ("One of user has already scheduled an interview at requested time interval. ");
    }

    public Interview deleteInterview (DeleteInterviewRequestDto requestDto, long id) {
        return null;
    }
}
