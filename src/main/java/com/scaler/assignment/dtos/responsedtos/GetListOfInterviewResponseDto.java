package com.scaler.assignment.dtos.responsedtos;

import com.scaler.assignment.models.Interview;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GetListOfInterviewResponseDto {

    List<Interview> interviewList;
}
