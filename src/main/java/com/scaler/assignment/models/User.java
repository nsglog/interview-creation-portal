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
public class User extends BaseModel {
    String name;
    String gender;
    String dob;
    String e_mail;
//    @ManyToMany
    @ManyToMany
    List<Interview> interviews;
}
