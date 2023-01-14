package com.scaler.assignment.services;

import com.scaler.assignment.models.User;
import com.scaler.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService (UserRepository userRepository)  {
        this.userRepository = userRepository;
    }

    public User addUser (User user) {
        userRepository.save(user);
        return user;
    }
}
