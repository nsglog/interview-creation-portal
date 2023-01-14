package com.scaler.assignment.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInterviewRequestDto {
    private String requestedBy;
    private String requestedWith;
}
