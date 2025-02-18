package com.example.restful_web_services.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//Specify the name name as user_details, user was causing problem in H2 as by default that table exists.
@Table(name = "user_details")
public class User {
    //Must have an ID for a Entity to use JPA
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

}
