package com.qa.todolist.service;

import java.util.ArrayList;
import java.util.List;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.repository.CategoryRepository;
import com.qa.todolist.dto.CategoryDTO;
import com.qa.todolist.mapper.CategoryMapper;

import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    private List<Category> categories;
    private List<CategoryDTO> categoryDTOs;

    private Category validCategory;
    private CategoryDTO validCategoryDTO;

    @BeforeEach
    public void init() {
        validCategory = new Category(1, "test", new ArrayList<>());
        validCategoryDTO = new CategoryDTO(1, "test");

        categories = new ArrayList<Category>();
        categoryDTOs = new ArrayList<CategoryDTO>();

        categoryRepository.deleteAll();

        validCategory = categoryRepository.save(validCategory);
        validCategoryDTO = categoryMapper.mapToDTO(validCategory);
        categories.add(validCategory);
        categoryDTOs.add(validCategoryDTO);
    }

    @Test
    void readAllCategoriesTest() {
        List<CategoryDTO> categoriesInDb = categoryService.readAllCategories();
        assertThat(categoryDTOs).isEqualTo(categoriesInDb);
    }

    @Test
    void readByIdTest() {
        assertThat(validCategoryDTO).isEqualTo(categoryService.readById(validCategory.getId()));
    }

    @Test
    void createCategory() {
        Category newCategory = new Category("new");
        assertThat(categoryMapper.mapToDTO(newCategory)).isEqualTo(categoryService.createCategory(newCategory));
    }

    @Test
    void updateCategory() {
        Category updatedCategory = categoryRepository.findAll().get(0);
        updatedCategory.setName("updated");
        CategoryDTO updatedDTO = categoryMapper.mapToDTO(updatedCategory);
        assertThat(updatedDTO).isEqualTo(categoryService.updateCategory(updatedCategory.getId(), updatedCategory));
    }

    @Test
    void deleteCategory() {
        assertThat(categoryService.deleteCategory(validCategory.getId())).isTrue();
    }

}