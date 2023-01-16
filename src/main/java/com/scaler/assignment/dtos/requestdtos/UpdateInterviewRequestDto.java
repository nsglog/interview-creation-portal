package com.scaler.assignment.dtos.requestdtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
public  class UpdateInterviewRequestDto {
    private Long bookWithId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime newStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime newEndTime;
}
