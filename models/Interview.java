package com.scaler.assignment.models;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Interview extends BaseModel {
    private Date startTime;
    private Date endTime;

    @ManyToMany(mappedBy = "User")
    List<User> bookedBy;
    @ManyToMany(mappedBy = "User")
    List<User> bookedWith;
}
