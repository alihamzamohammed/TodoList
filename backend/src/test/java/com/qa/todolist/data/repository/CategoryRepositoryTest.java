package com.qa.todolist.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.qa.todolist.data.model.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;
    private final Category TEST_CATEGORY = new Category(1, "Test");
    private Category testSavedCategory;
    private List<Category> testListCategory = new ArrayList<>();

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        if (!this.testListCategory.isEmpty()) {
            this.testListCategory.forEach(item -> this.testListCategory.remove(item));
        }
        this.testSavedCategory = this.repo.save(this.TEST_CATEGORY);
        this.testListCategory.add(testSavedCategory);
    }

    @Test
    void findByIdTest() {
        System.out.println();
        assertThat(this.repo.findById(this.testSavedCategory.getId())).contains(this.testSavedCategory);
    }

    @Test
    void findAllTest() {
        assertThat(this.repo.findAll()).isEqualTo(this.testListCategory);
    }

    @Test
    void createTest() {
        Category category1 = new Category(100, "hello");
        assertThat(this.repo.save(category1)).isEqualTo(category1);
    }

    @Test
    void updateTest() {
        Category updatedCategory = new Category(1, "bye");
        assertThat(this.repo.save(updatedCategory)).isEqualTo(updatedCategory);
    }

    @Test
    void deleteTest() {
        this.repo.delete(this.testSavedCategory);
        assertThat(this.repo.existsById(this.testSavedCategory.getId())).isFalse();
    }
}