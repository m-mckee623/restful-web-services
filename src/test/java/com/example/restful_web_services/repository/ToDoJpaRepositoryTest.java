package com.example.restful_web_services.repository;

import com.example.restful_web_services.pojo.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class ToDoJpaRepositoryTest {

    @Autowired
    private ToDoJpaRepository toDoJpaRepository;

    @Test
    @DisplayName("Test 1: Save ToDo Test")
    @Order(1)
    @Rollback(false)
    public void saveToDoTest() {

        //Action
        Todo todo = Todo.builder()
                .id(10001)
                .username("test")
                .description("This is a test")
                .targetDate(new Date())
                .isDone(false)
                .build();

        toDoJpaRepository.save(todo);

        long expectedResult = 10001;
        log.info("Test 1. Expected result, {}", expectedResult);
        long result = todo.getId();
        log.info("Test 1. Result, {}", result);
        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Test 2: Find ToDos by Username")
    @Order(2)
    public void findByUsernameTest() {

        //Action
        List<Todo> todos = toDoJpaRepository.findByUsername("test");
        long expectedResult = 10001;
        long result = todos.get(0).getId();
        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Test 3: Find ToDo by username and Id")
    @Order(3)
    public void findByUsernameAndIdTest() {

        //Action
        Optional<Todo> todo = toDoJpaRepository.findByUsernameAndId("test", 10001);

        long expectedResult = 10001;
        long result = todo.get().getId();
        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Test 4: Update ToDo by username and Id")
    @Order(4)
    public void updateByUsernameAndIdTest() {
        //Action
        Todo todoUpdated = Todo.builder()
                .id(10001)
                .username("test")
                .description("This description has been updated")
                .build();

        toDoJpaRepository.save(todoUpdated);

        //Action
        Optional<Todo> todo = toDoJpaRepository.findByUsernameAndId("test", 10001);
        String expectedResult = "This description has been updated";
        String result = todo.get().getDescription();
        assertEquals(expectedResult, result);

    }

    @Test
    @DisplayName("Test 5: Delete ToDo by username and Id")
    @Order(5)
    public void deleteByUsernameAndIdTest() {

        toDoJpaRepository.deleteByUsernameAndId("test",10001);

        //Action
        Optional<Todo> todo = toDoJpaRepository.findByUsernameAndId("test", 10001);
        assertTrue(todo.isEmpty());

    }

}