package com.qa.todolist.controller;

import java.util.List;

import com.qa.todolist.dto.TodoDTO;
import com.qa.todolist.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {
    
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> data = todoService.readAllTodos();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable("id") int id) {
        TodoDTO data = todoService.readById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody Todo todo) {
        TodoDTO newTodo = todoService.createTodo(todo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.valueOf(newTodo.getId()));
        return new ResponseEntity<>(newTodo, headers, HttpStatus.CREATED);
    }
}
