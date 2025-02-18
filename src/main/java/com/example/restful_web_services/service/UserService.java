package com.example.restful_web_services.service;

import com.example.restful_web_services.pojo.User;
import com.example.restful_web_services.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService {

    //Add dependency for the userRepository (DB queries)
    @Autowired
    private UserRepository userRepository;

    //This encodes the password
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        // Encrypt the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean validateUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            log.info("Retrieved User: {}, Password: {}", user.getUsername(), user.getPassword());
            // Use BCryptPasswordEncoder to validate the password
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}