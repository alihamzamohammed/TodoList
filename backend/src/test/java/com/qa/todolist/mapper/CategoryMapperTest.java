package com.qa.todolist.mapper;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.dto.CategoryDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryMapperTest {

    @Autowired
    CategoryMapper categoryMapper;
    Category category;
    CategoryDTO categoryDTO;

    @BeforeEach
    void setup() {
        category = new Category(1, "category");
        categoryDTO = new CategoryDTO(1, "category");
    }

    @Test
    void mapToDTOTest() {
        assertThat(categoryMapper.mapToDTO(category)).isEqualTo(categoryDTO);
    }

    @Test
    void mapToCategoryTest() {
        assertThat(categoryMapper.mapToCategory(categoryDTO)).isEqualTo(category);
    }
}
