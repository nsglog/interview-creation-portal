package com.scaler.assignment.repository;

import com.scaler.assignment.models.Interview;
import com.scaler.assignment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
    @Override
    Interview save (Interview interview);

    @Query(value = "SELECT * FROM interview WHERE (booked_by_id = ?1 OR " +
            "booked_with_id = ?1) AND"+
            " end_time > ?2 AND" +
            " start_time < ?3 ",
            nativeQuery = true)
    List<Interview> getInterviewList(Long userId, LocalDateTime start_time, LocalDateTime end_time);


    @Query(value = "SELECT * FROM interview WHERE booked_by_id = ?1 AND" +
            " end_time > ?2 AND" +
            " start_time < ?3 AND id != ?4",
            nativeQuery = true)
    List<Interview> findByParams(Long userId, LocalDateTime start_time, LocalDateTime end_time, Long interviewId);

    @Query (value = "select booke_by_id from interview where id = ?1", nativeQuery = true)
    Long getRequestingUser(long id);
}
