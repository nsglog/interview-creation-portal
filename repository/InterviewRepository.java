package com.scaler.assignment.repository;

import com.scaler.assignment.models.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    @Override
    public Interview save (Interview interview);
}
