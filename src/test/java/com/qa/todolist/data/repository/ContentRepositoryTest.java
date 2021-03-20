package com.qa.todolist.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.qa.todolist.data.model.Content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ContentRepositoryTest {

    @Autowired
    private ContentRepository repo;
    private final Content TEST_CONTENT = new Content(1, "Test");
    private Content testSavedContent;
    private List<Content> testListContent = new ArrayList<>();

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        if (!this.testListContent.isEmpty()) {
            this.testListContent.forEach(item -> this.testListContent.remove(item));
        }
        this.testSavedContent = this.repo.save(this.TEST_CONTENT);
        this.testListContent.add(testSavedContent);
    }

    @Test
    void findByIdTest() {
        System.out.println();
        assertThat(this.repo.findById(this.testSavedContent.getId())).contains(this.testSavedContent);
    }

    @Test
    void findAllTest() {
        assertThat(this.repo.findAll()).isEqualTo(this.testListContent);
    }

    @Test
    void createTest() {
        Content content1 = new Content(100, "hello");
        assertThat(this.repo.save(content1)).isEqualTo(content1);
    }

    @Test
    void updateTest() {
        Content updatedContent = new Content(1, "bye");
        assertThat(this.repo.save(updatedContent)).isEqualTo(updatedContent);
    }

    @Test
    void deleteTest() {
        this.repo.delete(this.testSavedContent);
        assertThat(this.repo.existsById(this.testSavedContent.getId())).isFalse();
    }
}