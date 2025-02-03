package com.example.restful_web_services.controller;


import com.example.restful_web_services.pojo.HelloWorld;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
//NOTE: This is linked to HelloWorldBean

//Add the Spring annotation, "RestController". Let Spring know this is a controller
@RestController
//CORS
@CrossOrigin(origins="http://localhost:4200")
public class HelloWorldController {

    //GET
    //method - Will return the path variable in the message
    @GetMapping(path = "/welcome-message/{name}")
    public HelloWorld helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorld(String.format("Welcome, %s, start planning your day", name));
    }
}
