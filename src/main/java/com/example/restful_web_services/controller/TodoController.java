package com.example.restful_web_services.controller;

import com.example.restful_web_services.pojo.Todo;
import com.example.restful_web_services.service.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoHardcodedService todoHardcodedService;

    @GetMapping("/todos")
    public List<Todo> getAllTodos(){
        return todoHardcodedService.findAllTodos();
    }
}
