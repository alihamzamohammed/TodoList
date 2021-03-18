package com.qa.todolist.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;
import com.qa.todolist.dto.ContentDTO;
import com.qa.todolist.dto.TitleDTO;
import com.qa.todolist.dto.TodoDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
        "classpath:test-data-todo.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class TodoControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Title title = new Title(1, "test");
    private Content content = new Content(1, "test");
    private TitleDTO titleDTO = new TitleDTO("test");
    private ContentDTO contentDTO = new ContentDTO("test");
    private Category category = new Category(1, "test");
    private Todo validTodo = new Todo(1, title, content, category);
    private TodoDTO validTodoDTO = new TodoDTO(1, titleDTO, contentDTO, category);

    private List<Todo> validTodos = List.of(validTodo);
    private List<TodoDTO> validTodoDTOs = List.of(validTodoDTO);

    @Test
    void createTodoTest() throws Exception {
        Title newTitle = new Title("test2");
        Content newContent = new Content("test2");
        TitleDTO newTitleDTO = new TitleDTO("test2");
        ContentDTO newContentDTO = new ContentDTO("test2");

        Todo todoToSave = new Todo(newTitle, newContent, category);
        TodoDTO expectedTodo = new TodoDTO(2, newTitleDTO, newContentDTO, category);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/todo");

        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(todoToSave));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(expectedTodo));

        ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location",
                String.valueOf(expectedTodo.getId()));

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher).andExpect(headerMatcher);
    }

    @Test
    void readAllCategoriesTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/todo");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(validTodoDTOs));

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

    }

    @Test
    void readTodoByIdTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/todo/1");
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(validTodoDTO));

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

    }

    @Test
    void updateTodoTest() throws Exception {
        Todo todoToUpdate = new Todo(title, content, category);
        TodoDTO expectedTodo = new TodoDTO(1, titleDTO, contentDTO, category);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/todo/1");

        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(todoToUpdate));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(expectedTodo));

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

    @Test
    void deleteTodoTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/todo/1");

        mockRequest.contentType(MediaType.TEXT_PLAIN);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

    }

}
