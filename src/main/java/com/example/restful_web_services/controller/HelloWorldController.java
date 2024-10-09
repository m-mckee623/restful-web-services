package com.example.restful_web_services.controller;


import com.example.restful_web_services.HelloWorld;
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

    //GET
    //URI - /hello-world-pojo
    //method - Will return the message input below
    @GetMapping(path = "hello-world-pojo")
    public HelloWorld helloWorldPojo() {
        return new HelloWorld("Hello World, this is my message to the world!");
    }
}
