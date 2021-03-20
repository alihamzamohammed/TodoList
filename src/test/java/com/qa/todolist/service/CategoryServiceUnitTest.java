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
import com.qa.todolist.data.repository.CategoryRepository;
import com.qa.todolist.exceptions.CategoryAlreadyExistsException;
import com.qa.todolist.exceptions.CategoryNotFoundException;
import com.qa.todolist.dto.CategoryDTO;
import com.qa.todolist.mapper.CategoryMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CategoryServiceUnitTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CategoryMapper categoryMapper;

    private List<Category> categorys;
    private List<CategoryDTO> categoryDTOs;

    private Category validCategory;
    private CategoryDTO validCategoryDTO;

    @BeforeEach
    public void setup() {
        validCategory = new Category(1, "test");
        validCategoryDTO = new CategoryDTO(1, "test");

        categorys = new ArrayList<Category>();
        categoryDTOs = new ArrayList<CategoryDTO>();

        categorys.add(validCategory);
        categoryDTOs.add(validCategoryDTO);
    }

    @Test
    void readAllCategorysTest() {
        when(categoryRepository.findAll()).thenReturn(categorys);
        when(categoryMapper.mapToDTO(Mockito.any(Category.class))).thenReturn(validCategoryDTO);

        assertThat(categoryDTOs).isEqualTo(categoryService.readAllCategories());

        verify(categoryRepository, times(1)).findAll();
        verify(categoryMapper, times(1)).mapToDTO(Mockito.any(Category.class));
    }

    @Test
    void readByIdTest() {
        Optional<Category> optional = Optional.of(validCategory);
        when(categoryRepository.findById(Mockito.anyInt())).thenReturn(optional);
        when(categoryMapper.mapToDTO(Mockito.any(Category.class))).thenReturn(validCategoryDTO);

        assertThat(validCategoryDTO).isEqualTo(categoryService.readById(validCategory.getId()));

        verify(categoryRepository, times(1)).findById(Mockito.anyInt());
        verify(categoryMapper, times(1)).mapToDTO(Mockito.any(Category.class));
    }

    @Test
    void createCategoryTest() {
        when(categoryRepository.existsByName(Mockito.anyString())).thenReturn(false);
        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(validCategory);
        when(categoryMapper.mapToDTO(Mockito.any(Category.class))).thenReturn(validCategoryDTO);

        assertThat(validCategoryDTO).isEqualTo(categoryService.createCategory(validCategory));

        verify(categoryRepository, times(1)).existsByName(Mockito.anyString());
        verify(categoryRepository, times(1)).save(Mockito.any(Category.class));
        verify(categoryMapper, times(1)).mapToDTO(Mockito.any(Category.class));
    }

    @Test
    void updateCategoryTest() {
        Optional<Category> optional = Optional.of(validCategory);
        when(categoryRepository.findById(Mockito.anyInt())).thenReturn(optional);
        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(validCategory);
        when(categoryMapper.mapToDTO(Mockito.any(Category.class))).thenReturn(validCategoryDTO);

        assertThat(validCategoryDTO).isEqualTo(categoryService.updateCategory(validCategory.getId(), validCategory));

        verify(categoryRepository, times(1)).findById(Mockito.anyInt());
        verify(categoryRepository, times(1)).save(Mockito.any(Category.class));
        verify(categoryMapper, times(1)).mapToDTO(Mockito.any(Category.class));
    }

    @Test
    void deleteCategoryTest() {
        when(categoryRepository.existsById(Mockito.anyInt())).thenReturn(true).thenReturn(false);
        assertThat(categoryService.deleteCategory(validCategory.getId())).isTrue();
        verify(categoryRepository, times(2)).existsById(Mockito.anyInt());
        verify(categoryRepository, times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    void deleteNullCategoryTest() {
        when(categoryRepository.existsById(Mockito.anyInt())).thenReturn(false);
        int id = validCategory.getId();
        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.deleteCategory(id);
        });
        verify(categoryRepository, times(1)).existsById(Mockito.anyInt());
    }

    @Test
    void updateNullCategoryTest() {
        when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        int id = validCategory.getId();
        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.updateCategory(id, validCategory);
        });
        verify(categoryRepository, times(1)).findById(Mockito.anyInt());
    }

    @Test
    void readByIdNullCategoryTest() {
        when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        int id = validCategory.getId();
        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.readById(id);
        });
        verify(categoryRepository, times(1)).findById(Mockito.anyInt());
    }

    @Test
    void createDuplicateCategoryTest() {
        when(categoryRepository.existsByName(Mockito.anyString())).thenReturn(true);
        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryService.createCategory(validCategory);
        });
        verify(categoryRepository, times(1)).existsByName(Mockito.anyString());
    }

    @Test
    void updateDuplicateCategoryTest() {
        when(categoryRepository.existsByName(Mockito.anyString())).thenReturn(true);
        int id = validCategory.getId();
        assertThrows(CategoryAlreadyExistsException.class, () -> {
            categoryService.updateCategory(id, validCategory);
        });
        verify(categoryRepository, times(1)).existsByName(Mockito.anyString());
    }
}
