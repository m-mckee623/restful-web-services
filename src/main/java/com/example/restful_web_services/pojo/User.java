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
@Table(name = "user_details")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

}
