package com.scaler.assignment.services;

import com.scaler.assignment.models.User;
import com.scaler.assignment.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService (UserRepository userRepository)  {
        this.userRepository = userRepository;
    }

    public User addUser (User user) {
        return null;
    }
}
