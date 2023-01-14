package com.scaler.assignment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Interview extends BaseModel {
    private Date startTime;
    private Date endTime;

    @ManyToMany
    private List<User> bookedBy;
    @ManyToMany
    private List<User> bookedWith;
}
