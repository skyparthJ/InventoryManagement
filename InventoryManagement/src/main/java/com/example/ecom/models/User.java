package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user1")
public class User extends BaseModel{
    private String name;
    private String email;
    private UserType userType;
}
