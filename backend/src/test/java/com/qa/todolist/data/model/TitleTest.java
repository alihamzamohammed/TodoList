package com.qa.todolist.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

class TitleTest {

    Title title;

    @BeforeEach
    void setup() {
        title = new Title("Testing");
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Title.class).verify();
    }

    @Test
    void titleTest() {
        assertThat(title).isNotNull().isInstanceOf(Title.class);
        assertThat(title.getTodoTitle()).isEqualTo("Testing");
    }

    @Test
    void titleWithIdTest() {
        assertThat(title).isNotNull().isInstanceOf(Title.class);
        assertThat(title.getTodoTitle()).isEqualTo("Testing");
        assertThat(title.getId()).isEqualTo(1);
    }

    @Test
    void getIdTest() {
        assertThat(title.getId()).isEqualTo(1);
    }

    @Test
    void setIdTest() {
        title.setId(2);
        assertThat(title.getId()).isEqualTo(2);
    }

    @Test
    void getTodoTitleTest() {
        assertThat(title.getTodoTitle()).isEqualTo("Testing");
    }

    @Test
    void setTodoTitleTest() {
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
        String toStringReturn = "{" + " todoTitle='" + "Testing" + "'" + "}";
        assertThat(title).hasToString(toStringReturn);
    }
}
