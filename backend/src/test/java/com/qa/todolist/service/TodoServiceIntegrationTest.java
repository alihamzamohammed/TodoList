package com.qa.todolist.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;
import com.qa.todolist.data.repository.CategoryRepository;
import com.qa.todolist.data.repository.ContentRepository;
import com.qa.todolist.data.repository.TitleRepository;
import com.qa.todolist.data.repository.TodoRepository;
import com.qa.todolist.dto.ContentDTO;
import com.qa.todolist.dto.TitleDTO;
import com.qa.todolist.dto.TodoDTO;
import com.qa.todolist.mapper.CategoryMapper;
import com.qa.todolist.mapper.ContentMapper;
import com.qa.todolist.mapper.TitleMapper;
import com.qa.todolist.mapper.TodoMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoServiceIntegrationTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private TitleMapper titleMapper;

    @Autowired
    private ContentMapper contentMapper;

    private List<Todo> todos;
    private List<TodoDTO> todoDTOs;

    private Title title;
    private Content content;
    private Category category;
    private TitleDTO titleDTO;
    private ContentDTO contentDTO;
    private Todo validTodo;
    private TodoDTO validTodoDTO;

    @BeforeEach
    public void init() {
        title = new Title("test");
        titleDTO = new TitleDTO("test");
        content = new Content("test");
        contentDTO = new ContentDTO("test");
        category = new Category("test");
        validTodo = new Todo(1, title, content, category);
        validTodoDTO = new TodoDTO(1, titleDTO, contentDTO, category);

        todos = new ArrayList<Todo>();
        todoDTOs = new ArrayList<TodoDTO>();

        titleRepository.deleteAll();
        contentRepository.deleteAll();
        categoryRepository.deleteAll();
        todoRepository.deleteAll();

        title = titleRepository.save(title);
        content = contentRepository.save(content);
        category = categoryRepository.save(category);

        titleDTO = titleMapper.mapToDTO(title);
        contentDTO = contentMapper.mapToDTO(content);

        validTodo = todoRepository.save(validTodo);
        validTodoDTO = todoMapper.mapToDTO(validTodo);
        validTodoDTO.setCategory(category);

        todos.add(validTodo);
        todoDTOs.add(validTodoDTO);
        category.setTodos(todos);
    }

    @Test
    void readAllTodosTest() {
        List<TodoDTO> todosInDb = todoService.readAllTodos();
        assertThat(todoDTOs).usingRecursiveComparison().isEqualTo(todosInDb);
    }

    @Test
    void readByIdTest() {
        assertThat(validTodoDTO).usingRecursiveComparison().isEqualTo(todoService.readById(validTodo.getId()));
    }

    @Test
    void createTodoTest() {
        Title newTitle = new Title("new");
        Content newContent = new Content("new");
        Todo newTodo = new Todo(newTitle, newContent, category);
        assertThat(todoMapper.mapToDTO(newTodo)).isEqualTo(todoService.createTodo(newTodo));
    }

    @Test
    void updateTodoTest() {
        Todo updatedTodo = todoRepository.findAll().get(0);
        Title newTitle = new Title("new");
        updatedTodo.setTitle(newTitle);
        TodoDTO updatedDTO = todoMapper.mapToDTO(updatedTodo);
        assertThat(updatedDTO).usingRecursiveComparison()
                .isEqualTo(todoService.updateTodo(updatedTodo.getId(), updatedTodo));
    }

    @Test
    void deleteTodoTest() {
        assertThat(todoService.deleteTodo(validTodoDTO.getId())).isTrue();
    }

}