package com.qa.todolist.data.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class TodoTest {

    static Title title;
    static Content content;
    static Category category;
    static Todo todo;

    @BeforeEach
    static void setup() {
        title = new Title("Testing");
        content = new Content("Testing");
        category = new Category("Testing");
        todo = new Todo(title, content, category);
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Todo.class).verify();
    }

    @Test
    void todoTest() {
        assertThat(todo).isNotNull().isInstanceOf(Todo.class);
        assertThat(todo.getTitle()).isEqualTo(title);
        assertThat(todo.getContent()).isEqualTo(content);
        assertThat(todo.getCategory()).isEqualTo(category);
    }

    @Test
    void todoWithIdTest() {
        assertThat(todo).isNotNull().isInstanceOf(Todo.class);
        assertThat(todo.getTitle()).isEqualTo(title);
        assertThat(todo.getContent()).isEqualTo(content);
        assertThat(todo.getCategory()).isEqualTo(category);
        assertThat(todo.getId()).isEqualTo(1);
    }

    @Test
    void getIdTest() {
        assertThat(todo.getId()).isEqualTo(1);
    }

    @Test
    void setIdTest() {
        todo.setId(2);
        assertThat(todo.getId()).isEqualTo(2);
    }

    @Test
    void getCategoryTest() {
        assertThat(todo.getCategory()).isEqualTo(category);
    }

    @Test
    void setCategoryTest() {
        Category newCat = new Category("Testing 2");
        todo.setCategory(newCat);
        assertThat(todo.getCategory()).isEqualTo(newCat);
    }

    @Test
    void getTitleTest() {
        assertThat(todo.getTitle()).isEqualTo(title);
    }

    @Test
    void setTitleTest() {
        Title newTitle = new Title("Testing 2");
        todo.setTitle(newTitle);
        assertThat(todo.getTitle()).isEqualTo(newTitle);
    }

    @Test
    void getContentTest() {
        assertThat(todo.getContent()).isEqualTo(content);
    }

    @Test
    void setContentTest() {
        Content newContent = new Content("Testing 2");
        todo.setContent(newContent);
        assertThat(todo.getContent()).isEqualTo(newContent);
    }

    @Test
    void hashCodeTest() {
        Todo todo1 = new Todo(title, content, category);
        Todo todo2 = new Todo(title, content, category);
        assertThat(todo1).hasSameHashCodeAs(todo2);
    }

    @Test
    void toStringTest() {
        assertThat(todo).hasToString("{" + " id='" + "1" + "'" + ", title='" + "Testing" + "'" + ", content='"
                + "Testing" + "'" + ", category='" + "Testing" + "'" + "}");
    }

}
