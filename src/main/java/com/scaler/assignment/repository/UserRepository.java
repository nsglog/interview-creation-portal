package com.scaler.assignment.repository;

import com.scaler.assignment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   @Override
   User save (User user);
   @Override
   Optional<User> findById(Long id);

}
