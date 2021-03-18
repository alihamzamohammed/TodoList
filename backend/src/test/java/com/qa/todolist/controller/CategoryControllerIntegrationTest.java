package com.qa.todolist.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todolist.data.model.Category;
import com.qa.todolist.dto.CategoryDTO;
import com.qa.todolist.mapper.CategoryMapper;

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
@Sql(scripts = { "classpath:test-schema-category.sql",
        "classpath:test-data-category.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Category validCategory = new Category(1, "test", List.of());
    private CategoryDTO validCategoryDTO = new CategoryDTO(1, "test");

    private List<Category> validCategories = List.of(validCategory);
    private List<CategoryDTO> validCategoryDTOs = List.of(validCategoryDTO);

    @Test
    void createCategoryTest() throws Exception {
        Category categoryToSave = new Category("test 2");
        CategoryDTO expectedCategory = new CategoryDTO(2, "test 2");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/category");

        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(categoryToSave));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(expectedCategory));

        ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location",
                String.valueOf(expectedCategory.getId()));

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher).andExpect(headerMatcher);
    }

    @Test
    void readAllCategoriesTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/category");
        validCategoryDTOs.forEach(cat -> cat.setTodos(new ArrayList<>()));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(validCategoryDTOs));

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

    }

    @Test
    void readCategoryByIdTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/category/1");
        mockRequest.accept(MediaType.APPLICATION_JSON);
        validCategoryDTOs.forEach(cat -> cat.setTodos(new ArrayList<>()));

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(validCategoryDTO));

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

    }

    @Test
    void updateCategoryTest() throws Exception {
        Category categoryToUpdate = new Category("test 2", new ArrayList<>());
        CategoryDTO expectedCategory = new CategoryDTO(1, "test 2");
        expectedCategory.setTodos(new ArrayList<>());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/category/1");

        mockRequest.contentType(MediaType.APPLICATION_JSON);
        mockRequest.content(objectMapper.writeValueAsString(categoryToUpdate));
        mockRequest.accept(MediaType.APPLICATION_JSON);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                .json(objectMapper.writeValueAsString(expectedCategory));

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
    }

    @Test
    void deleteCategoryTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/category/1");

        mockRequest.contentType(MediaType.TEXT_PLAIN);

        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();

        ResultMatcher contentMatcher = MockMvcResultMatchers.content().string("true");

        mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);

    }

}
