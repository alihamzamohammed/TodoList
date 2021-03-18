package com.qa.todolist.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import static org.assertj.core.api.Assertions.assertThat;

class ContentDTOTest {

    ContentDTO contentDTO;

    @BeforeEach
    void setup() {
        contentDTO = new ContentDTO("Testing");
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(ContentDTO.class).verify();
    }

    @Test
    void contentDTOTest() {
        ContentDTO contentDTO1 = new ContentDTO("Testing");
        assertThat(contentDTO1).isNotNull().isInstanceOf(ContentDTO.class);
        assertThat(contentDTO1.getTodoContent()).isEqualTo("Testing");
    }

    @Test
    void getTodoContentDTOTest() {
        assertThat(contentDTO.getTodoContent()).isEqualTo("Testing");
    }

    @Test
    void setTodoContentDTOTest() {
        contentDTO.setTodoContent("Testing 2");
        assertThat(contentDTO.getTodoContent()).isEqualTo("Testing 2");
    }

    @Test
    void hashCodeTest() {
        ContentDTO contentDTO1 = new ContentDTO("test");
        ContentDTO contentDTO2 = new ContentDTO("test");
        assertThat(contentDTO1).hasSameHashCodeAs(contentDTO2);
    }

    @Test
    void toStringTest() {
        ContentDTO contentDTO1 = new ContentDTO("test");
        String toStringReturn = "ContentDTO [todoContent=test]";
        assertThat(contentDTO1).hasToString(toStringReturn);
    }
}
