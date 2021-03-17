package com.qa.todolist.data.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(Content.class).verify();
    }

    @Test
    void contentTest() {
        Content content = new Content("Testing");
        assertThat(content).isNotNull().isInstanceOf(Content.class);
        assertThat(content.getTodoContent()).isEqualTo("Testing");
    }

    @Test
    void contentWithIdTest() {
        Content content = new Content(1, "Testing");
        assertThat(content).isNotNull().isInstanceOf(Content.class);
        assertThat(content.getTodoContent()).isEqualTo("Testing");
        assertThat(content.getId()).isEqualTo(1);
    }

    @Test
    void getIdTest() {
        Content content = new Content(1, "Testing");
        assertThat(content.getId()).isEqualTo(1);
    }

    @Test
    void setIdTest() {
        Content content = new Content(1, "Testing");
        content.setId(2);
        assertThat(content.getId()).isEqualTo(2);
    }

    @Test
    void getTodoContentTest() {
        Content content = new Content(1, "Testing");
        assertThat(content.getTodoContent()).isEqualTo("Testing");
    }

    @Test
    void setTodoContentTest() {
        Content content = new Content(1, "Testing");
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
        Content content1 = new Content("test");
        String toStringReturn = "{" + " todoContent='" + "test" + "'" + "}";
        assertThat(content1).hasToString(toStringReturn);
    }
}
