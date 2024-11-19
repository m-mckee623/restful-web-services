package com.example.restful_web_services.controller;

import com.example.restful_web_services.pojo.Todo;
import com.example.restful_web_services.repository.ToDoJpaRepository;
import com.example.restful_web_services.service.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoJpaController {

    @Autowired
    private ToDoJpaRepository toDoJpaRepository;

    @GetMapping("/jpa/users/{username}/todos")
    public ResponseEntity<List<Todo>> getAllTodos(@PathVariable String username) {
        try{
            List<Todo> todos = toDoJpaRepository.findByUsername(username);
            if(todos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(todos,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable String username,@PathVariable long id){
        Optional<Todo> todo = toDoJpaRepository.findByUsernameAndId(username, id);

        return todo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable String username, @PathVariable long id) {
        try {
            toDoJpaRepository.deleteBooksByUsernameAndId(username, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //EDIT/UPDATE
    @PutMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
        Optional<Todo> todoData = toDoJpaRepository.findByUsernameAndId(username, id);

        try{
            if(todoData.isPresent()){
                return new ResponseEntity<>(toDoJpaRepository.save(todo), HttpStatus.CREATED);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //CREATE/NEW
    @PostMapping("/jpa/users/{username}/todos")
    public ResponseEntity<HttpStatus> createTodo(
            @PathVariable String username, @RequestBody Todo todo){

        try{
            todo.setUsername(username);
            Todo createdTodo = toDoJpaRepository.save(todo);

            ///{id}
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
