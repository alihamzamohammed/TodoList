package com.qa.todolist.data.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {

    Content content;

    @BeforeEach
    void setup() {
        content = new Content("Testing");
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Content.class).verify();
    }

    @Test
    void contentTest() {
        assertThat(content).isNotNull().isInstanceOf(Content.class);
        assertThat(content.getTodoContent()).isEqualTo("Testing");
    }

    @Test
    void contentWithIdTest() {
        assertThat(content).isNotNull().isInstanceOf(Content.class);
        assertThat(content.getTodoContent()).isEqualTo("Testing");
        assertThat(content.getId()).isEqualTo(1);
    }

    @Test
    void getIdTest() {
        assertThat(content.getId()).isEqualTo(1);
    }

    @Test
    void setIdTest() {
        content.setId(2);
        assertThat(content.getId()).isEqualTo(2);
    }

    @Test
    void getTodoContentTest() {
        assertThat(content.getTodoContent()).isEqualTo("Testing");
    }

    @Test
    void setTodoContentTest() {
        content.setTodoContent("Testing 2");
        assertThat(content.getTodoContent()).isEqualTo("Testing 2");
    }

    @Test
    void hashCodeTest() {
        Content content1 = new Content("test");
        Content content2 = new Content("test");
        assertThat(content1).hasSameHashCodeAs(content2);
    }

    @Test
    void toStringTest() {
        String toStringReturn = "{" + " todoContent='" + "Testing" + "'" + "}";
        assertThat(content).hasToString(toStringReturn);
    }
}
