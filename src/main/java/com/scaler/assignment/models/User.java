package com.scaler.assignment.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
public class User extends BaseModel {
    String name;
    String gender;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime dob;
    String e_mail;
}
