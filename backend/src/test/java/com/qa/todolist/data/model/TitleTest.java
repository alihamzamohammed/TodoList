package com.qa.todolist.data.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

class TitleTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Title.class).verify();
    }

    @Test
    void titleTest() {
        Title title = new Title("Testing");
        assertThat(title).isNotNull().isInstanceOf(Title.class);
        assertThat(title.getTodoTitle()).isEqualTo("Testing");
    }

    @Test
    void titleWithIdTest() {
        Title title = new Title(1, "Testing");
        assertThat(title).isNotNull().isInstanceOf(Title.class);
        assertThat(title.getTodoTitle()).isEqualTo("Testing");
        assertThat(title.getId()).isEqualTo(1);
    }

    @Test
    void getIdTest() {
        Title title = new Title(1, "Testing");
        assertThat(title.getId()).isEqualTo(1);
    }

    @Test
    void setIdTest() {
        Title title = new Title(1, "Testing");
        title.setId(2);
        assertThat(title.getId()).isEqualTo(2);
    }

    @Test
    void getTodoTitleTest() {
        Title title = new Title(1, "Testing");
        assertThat(title.getTodoTitle()).isEqualTo("Testing");
    }

    @Test
    void setTodoTitleTest() {
        Title title = new Title(1, "Testing");
        title.setTodoTitle("Testing 2");
        assertThat(title.getTodoTitle()).isEqualTo("Testing 2");
    }

    @Test
    void hashCodeTest() {
        Title title1 = new Title("test");
        Title title2 = new Title("test");
        assertThat(title1).hasSameHashCodeAs(title2);
    }

    @Test
    void toStringTest() {
        Title title1 = new Title("test");
        String toStringReturn = "{" + " todoTitle='" + "test" + "'" + "}";
        assertThat(title1).hasToString(toStringReturn);
    }
}
