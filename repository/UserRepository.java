package com.scaler.assignment.repository;

import com.scaler.assignment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User save (User user);
}
