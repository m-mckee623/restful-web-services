package com.example.restful_web_services.repository;

import com.example.restful_web_services.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//The @Repository annotation in Java is used to indicate that a class is a data repository.
// It is typically used with classes that interact with the database.
//Handles DB methods, simple methods
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
