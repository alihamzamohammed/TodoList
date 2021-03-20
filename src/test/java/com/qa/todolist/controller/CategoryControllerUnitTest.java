package com.qa.todolist.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.dto.CategoryDTO;
import com.qa.todolist.mapper.CategoryMapper;
import com.qa.todolist.service.CategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class CategoryControllerUnitTest {

    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryMapper categoryMapper;

    private List<Category> categorys;
    private List<CategoryDTO> categoryDTOs;

    private Category validCategory;
    private CategoryDTO validCategoryDTO;

    @BeforeEach
    public void init() {
        validCategory = new Category(3, "test");
        validCategoryDTO = new CategoryDTO(3, "test");

        categorys = new ArrayList<>();
        categoryDTOs = new ArrayList<>();

        categorys.add(validCategory);
        categoryDTOs.add(validCategoryDTO);
    }

    @Test
    void deleteCategoryTest() {
        when(categoryService.deleteCategory(Mockito.any(Integer.class))).thenReturn(true);

        ResponseEntity<Boolean> response = new ResponseEntity<>(true, HttpStatus.OK);

        assertThat(response).isEqualTo(categoryController.deleteCategory(validCategory.getId()));

        verify(categoryService, times(1)).deleteCategory(Mockito.any(Integer.class));
    }

    @Test
    void updateCategoryTest() {
        when(categoryService.updateCategory(Mockito.any(Integer.class), Mockito.any(Category.class)))
                .thenReturn(validCategoryDTO);

        ResponseEntity<CategoryDTO> response = new ResponseEntity<>(validCategoryDTO, HttpStatus.OK);

        assertThat(response).isEqualTo(categoryController.updateCategory(validCategory.getId(), validCategory));

        verify(categoryService, times(1)).updateCategory(Mockito.any(Integer.class), Mockito.any(Category.class));
    }

    @Test
    void getAllCategoriesTest() {
        when(categoryService.readAllCategories()).thenReturn(categoryDTOs);

        ResponseEntity<List<CategoryDTO>> response = new ResponseEntity<>(categoryDTOs, HttpStatus.OK);

        assertThat(response).isEqualTo(categoryController.getAllCategories());

        verify(categoryService, times(1)).readAllCategories();
    }

    @Test
    void getCategoriesByIdTest() {
        when(categoryService.readById(Mockito.any(Integer.class))).thenReturn(validCategoryDTO);

        ResponseEntity<CategoryDTO> response = new ResponseEntity<>(validCategoryDTO, HttpStatus.OK);

        assertThat(response).isEqualTo(categoryController.getCategoryById(validCategory.getId()));

        verify(categoryService, times(1)).readById(Mockito.any(Integer.class));
    }

    @Test
    void createCategoryTest() {
        when(categoryService.createCategory(Mockito.any(Category.class))).thenReturn(validCategoryDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.valueOf(validCategory.getId()));
        ResponseEntity<CategoryDTO> response = new ResponseEntity<>(validCategoryDTO, headers, HttpStatus.CREATED);

        assertThat(response).isEqualTo(categoryController.createCategory(validCategory));

        verify(categoryService, times(1)).createCategory(Mockito.any(Category.class));
    }

}
