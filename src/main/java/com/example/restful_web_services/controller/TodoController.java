package com.example.restful_web_services.controller;

import com.example.restful_web_services.pojo.Todo;
import com.example.restful_web_services.service.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoController {

    @Autowired
    private TodoHardcodedService todoHardcodedService;

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoHardcodedService.findAllTodos();
    }

    @DeleteMapping("todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id){
        Todo todo = todoHardcodedService.deleteById(id);

        if(todo != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
