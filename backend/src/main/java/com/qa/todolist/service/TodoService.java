package com.qa.todolist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.qa.todolist.data.model.Todo;
import com.qa.todolist.data.repository.ContentRepository;
import com.qa.todolist.data.repository.TitleRepository;
import com.qa.todolist.data.repository.TodoRepository;
import com.qa.todolist.dto.TodoDTO;
import com.qa.todolist.exceptions.TodoItemNotFoundException;
import com.qa.todolist.mapper.ContentMapper;
import com.qa.todolist.mapper.TitleMapper;
import com.qa.todolist.mapper.TodoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private ContentRepository contentRepository;
    private ContentMapper contentMapper;

    private TitleRepository titleRepository;
    private TitleMapper titleMapper;

    private TodoRepository todoRepository;
    private TodoMapper todoMapper;

    @Autowired
    public TodoService(ContentRepository contentRepository, ContentMapper contentMapper,
            TitleRepository titleRepository, TitleMapper titleMapper, TodoRepository todoRepository,
            TodoMapper todoMapper) {
        this.contentRepository = contentRepository;
        this.contentMapper = contentMapper;

        this.titleRepository = titleRepository;
        this.titleMapper = titleMapper;

        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<TodoDTO> readAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoDTO> todoDTOs = new ArrayList<>();
        todos.forEach(todo -> todoDTOs.add(todoMapper.mapToDTO(todo)));
        return todoDTOs;
    }

    public TodoDTO readById(int id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            return todoMapper.mapToDTO(todo.get());
        } else {
            throw new TodoItemNotFoundException();
        }
    }

    public TodoDTO createTodo(Todo todo) {
        return todoMapper.mapToDTO(todoRepository.save(todo));
    }

    public TodoDTO updateTodo(int id, Todo todo) {
        Optional<Todo> optional = todoRepository.findById(id);
        if (optional.isPresent()) {
            Todo todoToUpdate = optional.get();
            todoToUpdate.setId(todo.getId());
            todoToUpdate.setTitle(todo.getTitle());
            todoToUpdate.setContent(todo.getContent());
            return todoMapper.mapToDTO(todoRepository.save(todoToUpdate));
        } else {
            throw new TodoItemNotFoundException();
        }
    }

    public Boolean deleteTodo(int id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
        } else {
            throw new TodoItemNotFoundException();
        }
        return !todoRepository.existsById(id);
    }

}
