package com.example.restful_web_services.service;

import com.example.restful_web_services.pojo.User;
import com.example.restful_web_services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean validateUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            System.out.println("Retrieved User: " + user.getUsername() + ", Password: " + user.getPassword());
            return password.equals(user.getPassword());
        }
        return false;
    }
}
