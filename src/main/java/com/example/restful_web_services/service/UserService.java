package com.example.restful_web_services.service;

import com.example.restful_web_services.pojo.User;
import com.example.restful_web_services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
            System.out.println("Retrieved User: " + user.getUsername() + ", Password: " + user.getPassword());
            // Use BCryptPasswordEncoder to validate the password
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}