package com.qa.todolist.data.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.qa.todolist.data.model.Title;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TitleRepositoryTest {

    @Autowired
    private TitleRepository repo;
    private final Title TEST_TITLE = new Title(1, "Test");
    private Title testSavedTitle;
    private List<Title> testListTitle = new ArrayList<>();

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        if (!this.testListTitle.isEmpty()) {
            this.testListTitle.forEach(item -> this.testListTitle.remove(item));
        }
        this.testSavedTitle = this.repo.save(this.TEST_TITLE);
        this.testListTitle.add(testSavedTitle);
    }

    @Test
    void findByIdTest() {
        System.out.println();
        assertThat(this.repo.findById(this.testSavedTitle.getId())).contains(this.testSavedTitle);
    }

    @Test
    void findAllTest() {
        assertThat(this.repo.findAll()).isEqualTo(this.testListTitle);
    }

    @Test
    void createTest() {
        Title title1 = new Title(2, "hello");
        assertThat(this.repo.save(title1)).isEqualTo(title1);
    }

    @Test
    void updateTest() {
        Title updatedTitle = new Title(1, "bye");
        assertThat(this.repo.save(updatedTitle)).isEqualTo(updatedTitle);
    }

    @Test
    void deleteTest() {
        this.repo.delete(this.testSavedTitle);
        assertThat(this.repo.existsById(this.testSavedTitle.getId())).isFalse();
    }
}