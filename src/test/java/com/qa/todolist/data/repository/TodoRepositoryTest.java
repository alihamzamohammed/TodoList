package com.qa.todolist.data.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository repo;
    @Autowired
    private ContentRepository contentRepo;
    @Autowired
    private TitleRepository titleRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    private final Title title = new Title("test");
    private final Content content = new Content("test");
    private final Category category = new Category("test");
    private final Todo TEST_TODO = new Todo(title, content, category);
    private Todo testSavedTodo;

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.categoryRepo.deleteAll();
        this.titleRepo.deleteAll();
        this.contentRepo.deleteAll();

        this.categoryRepo.save(category);
        this.titleRepo.save(title);
        this.contentRepo.save(content);
        this.testSavedTodo = this.repo.save(this.TEST_TODO);

    }

    @Test
    void findByIdTest() {
        System.out.println();
        assertThat(this.repo.findById(this.testSavedTodo.getId())).contains(this.testSavedTodo);
    }

    @Test
    void findAllTest() {
        assertThat(this.repo.findAll()).isEqualTo(List.of(TEST_TODO));
    }

    @Test
    void createTest() {
        Todo todo1 = this.repo.save(new Todo(title, content, category));
        assertThat(this.repo.findById(todo1.getId())).contains(todo1);
    }

    @Test
    void updateTest() {
        Todo updatedTodo = new Todo(1, title, content, category);
        assertThat(this.repo.save(updatedTodo)).isEqualTo(updatedTodo);
    }

    @Test
    void deleteTest() {
        this.repo.delete(this.testSavedTodo);
        assertThat(this.repo.existsById(this.testSavedTodo.getId())).isFalse();
    }
}