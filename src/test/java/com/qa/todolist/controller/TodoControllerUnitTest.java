package com.qa.todolist.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;
import com.qa.todolist.dto.ContentDTO;
import com.qa.todolist.dto.TitleDTO;
import com.qa.todolist.dto.TodoDTO;
import com.qa.todolist.mapper.TodoMapper;
import com.qa.todolist.service.TodoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class TodoControllerUnitTest {

    @Autowired
    private TodoController todoController;

    @MockBean
    private TodoService todoService;

    @MockBean
    private TodoMapper todoMapper;

    private List<Todo> todos;
    private List<TodoDTO> todoDTOs;

    private Todo validTodo;
    private TodoDTO validTodoDTO;
    private Title title;
    private Content content;
    private Category category;
    private TitleDTO titleDTO;
    private ContentDTO contentDTO;

    @BeforeEach
    public void init() {
        title = new Title("test");
        content = new Content("test");
        category = new Category("test");
        titleDTO = new TitleDTO("test");
        contentDTO = new ContentDTO("test");
        validTodo = new Todo(3, title, content, category);
        validTodoDTO = new TodoDTO(3, titleDTO, contentDTO, category);

        todos = new ArrayList<Todo>();
        todoDTOs = new ArrayList<TodoDTO>();

        todos.add(validTodo);
        todoDTOs.add(validTodoDTO);
    }

    @Test
    void deleteTodoTest() {
        when(todoService.deleteTodo(Mockito.any(Integer.class))).thenReturn(true);

        ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);

        assertThat(response).isEqualTo(todoController.deleteTodo(validTodo.getId()));

        verify(todoService, times(1)).deleteTodo(Mockito.any(Integer.class));
    }

    @Test
    void updateTodoTest() {
        when(todoService.updateTodo(Mockito.any(Integer.class), Mockito.any(Todo.class))).thenReturn(validTodoDTO);

        ResponseEntity<TodoDTO> response = new ResponseEntity<TodoDTO>(validTodoDTO, HttpStatus.OK);

        assertThat(response).isEqualTo(todoController.updateTodo(validTodo.getId(), validTodo));

        verify(todoService, times(1)).updateTodo(Mockito.any(Integer.class), Mockito.any(Todo.class));
    }

    @Test
    void getAllTodosTest() {
        when(todoService.readAllTodos()).thenReturn(todoDTOs);

        ResponseEntity<List<TodoDTO>> response = new ResponseEntity<List<TodoDTO>>(todoDTOs, HttpStatus.OK);

        assertThat(response).isEqualTo(todoController.getAllTodos());

        verify(todoService, times(1)).readAllTodos();
    }

    @Test
    void getTodosByIdTest() {
        when(todoService.readById(Mockito.any(Integer.class))).thenReturn(validTodoDTO);

        ResponseEntity<TodoDTO> response = new ResponseEntity<TodoDTO>(validTodoDTO, HttpStatus.OK);

        assertThat(response).isEqualTo(todoController.getTodoById(validTodo.getId()));

        verify(todoService, times(1)).readById(Mockito.any(Integer.class));
    }

    @Test
    void createTodoTest() {
        when(todoService.createTodo(Mockito.any(Todo.class))).thenReturn(validTodoDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.valueOf(validTodo.getId()));
        ResponseEntity<TodoDTO> response = new ResponseEntity<TodoDTO>(validTodoDTO, headers, HttpStatus.CREATED);

        assertThat(response).isEqualTo(todoController.createTodo(validTodo));

        verify(todoService, times(1)).createTodo(Mockito.any(Todo.class));
    }

}
