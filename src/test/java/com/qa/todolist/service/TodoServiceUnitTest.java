package com.qa.todolist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;
import com.qa.todolist.data.repository.TodoRepository;
import com.qa.todolist.dto.ContentDTO;
import com.qa.todolist.dto.TitleDTO;
import com.qa.todolist.dto.TodoDTO;
import com.qa.todolist.exceptions.TodoItemNotFoundException;
import com.qa.todolist.mapper.TodoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TodoServiceUnitTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepository;

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
    public void setup() {
        title = new Title("test");
        content = new Content("test");
        category = new Category("test");
        titleDTO = new TitleDTO("test");
        contentDTO = new ContentDTO("test");
        validTodo = new Todo(1, title, content, category);
        validTodoDTO = new TodoDTO(3, titleDTO, contentDTO, category);

        todos = new ArrayList<Todo>();
        todoDTOs = new ArrayList<TodoDTO>();

        todos.add(validTodo);
        todoDTOs.add(validTodoDTO);
    }

    @Test
    void readAllTodosTest() {
        when(todoRepository.findAll()).thenReturn(todos);
        when(todoMapper.mapToDTO(Mockito.any(Todo.class))).thenReturn(validTodoDTO);

        assertThat(todoDTOs).isEqualTo(todoService.readAllTodos());

        verify(todoRepository, times(1)).findAll();
        verify(todoMapper, times(1)).mapToDTO(Mockito.any(Todo.class));
    }

    @Test
    void readByIdTest() {
        Optional<Todo> optional = Optional.of(validTodo);
        when(todoRepository.findById(Mockito.any(Integer.class))).thenReturn(optional);
        when(todoMapper.mapToDTO(Mockito.any(Todo.class))).thenReturn(validTodoDTO);

        assertThat(validTodoDTO).isEqualTo(todoService.readById(validTodo.getId()));

        verify(todoRepository, times(1)).findById(Mockito.any(Integer.class));
        verify(todoMapper, times(1)).mapToDTO(Mockito.any(Todo.class));
    }

    @Test
    void createTodoTest() {
        when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(validTodo);
        when(todoMapper.mapToDTO(Mockito.any(Todo.class))).thenReturn(validTodoDTO);

        assertThat(validTodoDTO).isEqualTo(todoService.createTodo(validTodo));

        verify(todoRepository, times(1)).save(Mockito.any(Todo.class));
        verify(todoMapper, times(1)).mapToDTO(Mockito.any(Todo.class));
    }

    @Test
    void updateTodoTest() {
        Optional<Todo> optional = Optional.of(validTodo);
        when(todoRepository.findById(Mockito.any(Integer.class))).thenReturn(optional);
        when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(validTodo);
        when(todoMapper.mapToDTO(Mockito.any(Todo.class))).thenReturn(validTodoDTO);

        assertThat(validTodoDTO).isEqualTo(todoService.updateTodo(validTodo.getId(), validTodo));

        verify(todoRepository, times(1)).findById(Mockito.any(Integer.class));
        verify(todoRepository, times(1)).save(Mockito.any(Todo.class));
        verify(todoMapper, times(1)).mapToDTO(Mockito.any(Todo.class));
    }

    @Test
    void deleteTodoTest() {
        when(todoRepository.existsById(Mockito.any(Integer.class))).thenReturn(true).thenReturn(false);
        assertThat(todoService.deleteTodo(validTodo.getId())).isTrue();
        verify(todoRepository, times(2)).existsById(Mockito.any(Integer.class));
        verify(todoRepository, times(1)).deleteById(Mockito.any(Integer.class));
    }

    @Test
    void deleteNullTodoTest() {
        when(todoRepository.existsById(Mockito.any(Integer.class))).thenReturn(false);
        int id = validTodo.getId();
        assertThrows(TodoItemNotFoundException.class, () -> {
            todoService.deleteTodo(id);
        });
        verify(todoRepository, times(1)).existsById(Mockito.any(Integer.class));
    }

    @Test
    void updateNullTodoTest() {
        when(todoRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        int id = validTodo.getId();
        assertThrows(TodoItemNotFoundException.class, () -> {
            todoService.updateTodo(id, validTodo);
        });
        verify(todoRepository, times(1)).findById(Mockito.any(Integer.class));
    }

    @Test
    void readByIdNullTodoTest() {
        when(todoRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        int id = validTodo.getId();
        assertThrows(TodoItemNotFoundException.class, () -> {
            todoService.readById(id);
        });
        verify(todoRepository, times(1)).findById(Mockito.any(Integer.class));
    }
}
