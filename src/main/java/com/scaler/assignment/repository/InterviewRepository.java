package com.scaler.assignment.repository;

import com.scaler.assignment.models.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    @Override
    Interview save (Interview interview);

    @Query(value = "SELECT * FROM interview WHERE booked_by_id = ?1 " +
            "OR booked_with_id = ?1 " +
            "AND start_time < ?3 " +
            "AND end_time > ?2",
            nativeQuery = true)
    List<Interview> findByUser(Long userId, LocalDateTime start_time, LocalDateTime end_time);

    @Query(value = "SELECT * FROM interview WHERE booked_by_id = ?1 " +
            "OR booked_with_id = ?1 " +
            "AND start_time < ?3 " +
            "AND end_time > ?2" +
            "AND id != ?4",
            nativeQuery = true)
    List<Interview> findByUser(Long userId, LocalDateTime start_time, LocalDateTime end_time, Long interviewId);
}
