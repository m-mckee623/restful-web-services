package com.example.restful_web_services.service;

import com.example.restful_web_services.pojo.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//A business service
@Service
public class TodoHardcodedService {

    private static List<Todo> todos = new ArrayList();
    private static int idCounter = 0;

    static {
        todos.add(new Todo(++idCounter,"McKee","Learn Angular", new Date(),false));
        todos.add(new Todo(++idCounter,"McKee","Learn Java", new Date(),false));
        todos.add(new Todo(++idCounter,"McKee","Learn Spring Boot", new Date(),false));
        todos.add(new Todo(++idCounter,"McKee","Learn AWS", new Date(),false));
    }

    public List<Todo> findAllTodos(){
        return todos;
    }
}
