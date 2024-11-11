package com.example.restful_web_services.controller;

import com.example.restful_web_services.pojo.Todo;
import com.example.restful_web_services.service.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

//TODO: ADD USERNAME AS PATH VARIABLE FOR THE NECESSARY CALLS. First get this all functioning basic.

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoController {

    @Autowired
    private TodoHardcodedService todoHardcodedService;

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoHardcodedService.findAllTodos();
    }

    @GetMapping("/todos/{id}")
    public Todo getTodo(@PathVariable long id){
        return todoHardcodedService.findById(id);
    }

    @DeleteMapping("todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable long id){
        Todo todo = todoHardcodedService.deleteById(id);

        if(todo != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    //EDIT/UPDATE
    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable long id, @RequestBody Todo todo){
        Todo todoUpdated = todoHardcodedService.save(todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    //CREATE/NEW
    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        Todo createdTodo = todoHardcodedService.save(todo);

        //Get current resource URL
        //url
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
