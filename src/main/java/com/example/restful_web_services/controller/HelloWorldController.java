package com.example.restful_web_services.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Add the Spring annotation, "RestController". Let Spring know this is a controller
@RestController
public class HelloWorldController {

    //GET
    //URI - /hello-world
    //method - "Hello World". Will simply write a method to return a String
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}
