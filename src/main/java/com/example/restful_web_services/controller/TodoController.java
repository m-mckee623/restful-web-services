package com.example.restful_web_services.controller;

import com.example.restful_web_services.pojo.Todo;
import com.example.restful_web_services.repository.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
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
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TodoController {

    @Autowired
    private ToDoRepository toDoRepository;

    //Step 5. The backend server processes the request and sends back a response.
    //E.g. http://localhost:8080/users/leonardo/todos
    @GetMapping("/users/{username}/todos")
    public ResponseEntity<List<Todo>> getAllTodos(@PathVariable String username) {
        try{
            log.info("In method for getAllTodos. Username, {}", username);
            List<Todo> todos = toDoRepository.findByUsername(username);
            if(todos.isEmpty()){
                log.info("In method for getAllTodos. No data found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("In method for getAllTodos. Todos found.");
            return new ResponseEntity<>(todos,HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable String username,@PathVariable long id){

        try {
            log.info("In getTodo method, Passed params: username, {}  id, {}", username, id);
            Optional<Todo> todo = toDoRepository.findByUsernameAndId(username, id);
            if(todo.isPresent()){
                log.info("Found data. (getTodo)");
                return new ResponseEntity<>(todo.get(),HttpStatus.OK);
            }else {
                log.info("No data found. (getTodo)");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable String username, @PathVariable long id) {
        try {
            log.info("In method for deleteTodo. Username, {}, Id, {}", username, id);
            toDoRepository.deleteByUsernameAndId(username, id);
            log.info("Data deleted.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.info("Data not found. Data not deleted");
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //EDIT/UPDATE
    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
        Optional<Todo> todoData = toDoRepository.findByUsernameAndId(username, id);

        try{
            log.info("In method for updateTodo. Username, {}, id, {}", username, id);
            log.info("Data being updated. Todo, {}", todo.toString());
            if(todoData.isPresent()){
                log.info("Data successfully updated");
                return new ResponseEntity<>(toDoRepository.save(todo), HttpStatus.CREATED);
            } else
                log.info("Data not found. Data not successfully updated");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //CREATE/NEW
    @PostMapping("/users/{username}/todos")
    public ResponseEntity<HttpStatus> createTodo(
            @PathVariable String username, @RequestBody Todo todo){

        try{
            log.info("In method for createTodo. Username, {}, todo, {}", username, todo.toString());
            todo.setUsername(username);

            Todo createdTodo = toDoRepository.save(todo);
            log.info("Data successfully created");
            ///{id}
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
