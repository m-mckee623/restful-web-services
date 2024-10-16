package com.example.restful_web_services.controller;


import com.example.restful_web_services.HelloWorld;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Add the Spring annotation, "RestController". Let Spring know this is a controller
@RestController
//CORS
@CrossOrigin(origins="http://localhost:4200")
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
        // Below was to show the error worked.
       // throw new RuntimeException("Something went wrong");
    }

    //GET
    //URI - /hello-world/path-variable/{name}
    //method - Will return the path variable in the message
    @GetMapping(path = "hello-world/path-variable/{name}")
    public HelloWorld helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorld(String.format("Hello World, %s", name));
    }
}
