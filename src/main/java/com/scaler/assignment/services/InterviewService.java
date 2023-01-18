package com.scaler.assignment.services;

import com.scaler.assignment.dtos.requestdtos.CreateInterviewRequestDto;
import com.scaler.assignment.dtos.requestdtos.DeleteInterviewRequestDto;
import com.scaler.assignment.dtos.requestdtos.UpdateInterviewRequestDto;
import com.scaler.assignment.exceptions.*;
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

    public List<Interview> getListOfInterview()  {

        List<Interview> interviewList = interviewRepository.findAll();
        return interviewList;
    }

    public Interview createInterview (CreateInterviewRequestDto requestDto) throws Exception {

        System.out.println("debug 1+1+1");

        Long requestingUserId = requestDto.getRequestedById();
        Long requestedUserId = requestDto.getRequestedToId();

        User requestingUser = userRepository.findById(requestingUserId).get();
        User requestedUser = userRepository.findById(requestedUserId).get();

        LocalDateTime interviewStartTime = requestDto.getStartTime();
        LocalDateTime interviewEndTime = requestDto.getEndTime();

        if(interviewStartTime.compareTo(interviewEndTime) >= 0 ||
                interviewStartTime.compareTo(LocalDateTime.now()) < 0) {
            throw new InvalidDatesException ("Invalid Dates");
        }

        if(requestingUser.equals(requestedUser)) {
            throw new SameUserException("You cannot set meeting with yourself. Talk to mirror better go see a doctor");
        }

        System.out.println(interviewStartTime+" "+interviewEndTime);

        List<Interview> interviewsOfRequestingUser = interviewRepository.getInterviewList(requestingUser.getId(),
                interviewStartTime,
                interviewEndTime);
        List<Interview> interviewsOfRequestedUser = interviewRepository.getInterviewList(requestedUser.getId(),
                interviewStartTime,
                interviewEndTime);

        for(Interview interview : interviewsOfRequestedUser)    {

            System.out.println("booked by "+interview.getBookedBy().getId()
                    +"booked with "+interview.getBookedWith().getId()
                    +"start date and time "+interview.getStartTime()
                    +"end date and time " + interview.getEndTime());
        }

        for(Interview interview : interviewsOfRequestingUser)    {

            System.out.println("booked by "+interview.getBookedBy().getId()
                    +" booked with "+interview.getBookedWith().getId()
                    +" start date and time "+interview.getStartTime()
                    +" end date and time " + interview.getEndTime());
        }

        if(interviewsOfRequestingUser.isEmpty() && interviewsOfRequestedUser.isEmpty()) {

            Interview interview = new Interview();
            setInterview (interview, interviewStartTime, interviewEndTime, requestingUser, requestedUser);

            return interview;
        }

        throw new ConflictOfTimingException("One of user has already scheduled an interview at requested time interval. ");
    }

    private Interview setInterview (Interview interview, LocalDateTime startTime, LocalDateTime endTime,
                                    User requestedBy, User requestedWith)    {

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

        if(bookWithUser.equals(requestingUser)) {
            throw new SameUserException("You cannot set meeting with yourself. Talk to mirror better go see a doctor");
        }

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

            setInterview(scheduledInterview, start, end, requestingUser, bookWithUser);

            return scheduledInterview;
        }

        throw new ConflictOfTimingException ("One of user has already scheduled an interview at requested time interval. ");
    }

    public Interview deleteInterview (DeleteInterviewRequestDto requestDto, long id) {
        return null;
    }
}
