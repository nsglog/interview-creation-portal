package com.scaler.assignment.dtos.responsedtos;

import com.scaler.assignment.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetListOfUserResponseDto {
    private List<User> userList;
}
