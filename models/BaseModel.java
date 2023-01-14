package com.scaler.assignment.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel {
    @Id
    long id;
}
