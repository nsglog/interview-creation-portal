package com.scaler.assignment.models;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class User extends BaseModel {
    String name;
    String gender;
    Date dob;
    String e_mail;
    @ManyToMany
    List<Interview> interviews;
}
