package com.example.restful_web_services.controller;

import com.example.restful_web_services.pojo.Todo;
import com.example.restful_web_services.repository.ToDoJpaRepository;
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
public class TodoJpaController {

    @Autowired
    private ToDoJpaRepository toDoJpaRepository;

    @GetMapping("/jpa/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return toDoJpaRepository.findByUsername(username);
    }

    @GetMapping("/jpa/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username,@PathVariable long id){
        return toDoJpaRepository.findByUsernameAndId(username, id).get();
    }

    @DeleteMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){

         toDoJpaRepository.deleteBooksByTitle(username, id);

         return ResponseEntity.noContent().build();    }

    //EDIT/UPDATE
    @PutMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
        toDoJpaRepository.save(todo);

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    //CREATE/NEW
    @PostMapping("/jpa/users/{username}/todos")
    public ResponseEntity<Void> createTodo(
            @PathVariable String username, @RequestBody Todo todo){

        //Todo createdTodo = todoService.save(todo);
        todo.setUsername(username);
        Todo createdTodo = toDoJpaRepository.save(todo);

        //Location
        //Get current resource url
        ///{id}
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
