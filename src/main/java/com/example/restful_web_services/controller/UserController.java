package com.example.restful_web_services.controller;

import com.example.restful_web_services.pojo.User;
import com.example.restful_web_services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User getUser(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        boolean isValidUser = userService.validateUser(username, password);

        Map<String, String> response = new HashMap<>();
        if (isValidUser) {
            response.put("message", "Login successful");
            response.put("username", username);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());
        Map<String, String> response = new HashMap<>();
        if (existingUser != null) {
            response.put("message", "Username already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        userService.saveUser(user);
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }
}