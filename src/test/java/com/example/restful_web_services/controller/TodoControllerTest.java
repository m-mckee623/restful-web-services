package com.example.restful_web_services.controller;

import com.example.restful_web_services.pojo.Todo;
import com.example.restful_web_services.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TodoControllerTest {

    @InjectMocks
    private TodoController todoController;

    @Mock
    private ToDoRepository toDoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTodos() {
        String username = "testuser";
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1L, "username", "Test Todo", new Date(), false));

        when(toDoRepository.findByUsername(username)).thenReturn(todos);

        ResponseEntity<List<Todo>> response = todoController.getAllTodos(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todos, response.getBody());
    }

    @Test
    void testGetTodo() {
        String username = "testuser";
        long id = 1L;
        Todo todo = new Todo(id, username, "Test Todo",new Date(), false);

        when(toDoRepository.findByUsernameAndId(username, id)).thenReturn(Optional.of(todo));

        ResponseEntity<Todo> response = todoController.getTodo(username, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(todo, response.getBody());
    }

    @Test
    void testDeleteTodo() {
        String username = "testuser";
        long id = 1L;

        doNothing().when(toDoRepository).deleteByUsernameAndId(username, id);

        ResponseEntity<HttpStatus> response = todoController.deleteTodo(username, id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testUpdateTodo() {
        String username = "testuser";
        long id = 1L;
        Todo todo = new Todo(id, username, "Updated Test Todo",new Date(), true);

        when(toDoRepository.findByUsernameAndId(username, id)).thenReturn(Optional.of(todo));
        when(toDoRepository.save(todo)).thenReturn(todo);

        ResponseEntity<Todo> response = todoController.updateTodo(username, id, todo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(todo, response.getBody());
    }

    @Test
    void testCreateTodo() {
        String username = "testuser";
        Todo todo = new Todo(1L, username, "New Test Todo", new Date(), false);

        when(toDoRepository.save(any(Todo.class))).thenReturn(todo);

        ResponseEntity<HttpStatus> response = todoController.createTodo(username, todo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}