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
        todos.add(new Todo(++idCounter, "username", "Learn Angular", new Date(), false));
        todos.add(new Todo(++idCounter, "username", "Learn Java", new Date(), false));
        todos.add(new Todo(++idCounter, "username", "Learn Spring Boot", new Date(), false));
        todos.add(new Todo(++idCounter, "username", "Learn AWS", new Date(), false));
    }

    public List<Todo> findAllTodos() {
        return todos;
    }

    public Todo deleteById(long id) {
        Todo todo = findById(id);
        if (todo != null) {
            todos.remove(todo);
            return todo;
        }
        return null;
    }

    public Todo findById(long id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    public Todo save(Todo todo){
        if(todo.getId()==-1 || todo.getId()==0){
            todo.setId(++idCounter);
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }
        return todo;
    }

}
