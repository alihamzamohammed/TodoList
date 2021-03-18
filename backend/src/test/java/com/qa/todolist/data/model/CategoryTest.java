package com.qa.todolist.data.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class CategoryTest {

    Category category;
    List<Todo> todos;

    @BeforeEach
    void setup() {
        category = new Category(1, "Testing", todos);
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Category.class)
                .withPrefabValues(Category.class, new Category("1"), new Category("2")).verify();
    }

    @Test
    void categoryTest() {
        Category category1 = new Category("Testing");
        assertThat(category1).isNotNull().isInstanceOf(Category.class);
        assertThat(category1.getName()).isEqualTo("Testing");
    }

    @Test
    void categoryWithIdTest() {
        Category category1 = new Category(1, "Testing");
        assertThat(category1).isNotNull().isInstanceOf(Category.class);
        assertThat(category1.getName()).isEqualTo("Testing");
        assertThat(category1.getId()).isEqualTo(1);
    }

    @Test
    void categoryWithTodosTest() {
        Category category1 = new Category(1, "Testing", todos);
        assertThat(category1).isNotNull().isInstanceOf(Category.class);
        assertThat(category1.getName()).isEqualTo("Testing");
        assertThat(category1.getId()).isEqualTo(1);
        assertThat(category1.getTodos()).isEqualTo(todos);
    }

    @Test
    void categoryWithNameTodosTest() {
        Category category1 = new Category("Testing", todos);
        assertThat(category1).isNotNull().isInstanceOf(Category.class);
        assertThat(category1.getName()).isEqualTo("Testing");
        assertThat(category1.getTodos()).isEqualTo(todos);
    }

    @Test
    void getIdTest() {
        assertThat(category.getId()).isEqualTo(1);
    }

    @Test
    void setIdTest() {
        category.setId(2);
        assertThat(category.getId()).isEqualTo(2);
    }

    @Test
    void getNameTest() {
        assertThat(category.getName()).isEqualTo("Testing");
    }

    @Test
    void setNameTest() {
        category.setName("Testing 2");
        assertThat(category.getName()).isEqualTo("Testing 2");
    }

    @Test
    void getTodosTest() {
        assertThat(category.getTodos()).isEqualTo(todos);
    }

    @Test
    void setTodosTest() {
        Title title = new Title("Testing");
        Content content = new Content("Testing");
        Todo todo = new Todo(1, title, content, category);
        List<Todo> todos1 = List.of(todo);
        category.setTodos(todos1);
        assertThat(category.getTodos()).isEqualTo(todos1);
    }

    @Test
    void hashCodeTest() {
        Category category1 = new Category("test");
        Category category2 = new Category("test");
        assertThat(category1).hasSameHashCodeAs(category2);
    }

    @Test
    void toStringTest() {
        Category category1 = new Category(1, "test");
        String toStringReturn = "Category [name=test, todos=null]";
        assertThat(category1).hasToString(toStringReturn);
    }

}
