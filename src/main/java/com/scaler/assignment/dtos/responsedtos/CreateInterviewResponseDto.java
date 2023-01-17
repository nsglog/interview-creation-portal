package com.scaler.assignment.dtos.responsedtos;

import com.scaler.assignment.models.Interview;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CreateInterviewResponseDto {
    private Interview interview;
    private String message;
}
