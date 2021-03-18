package com.qa.todolist.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

class TitleDTOTest {

    TitleDTO titleDTO;

    @BeforeEach
    void setup() {
        titleDTO = new TitleDTO("Testing");
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(TitleDTO.class).verify();
    }

    @Test
    void titleDTOTest() {
        TitleDTO titleDTO1 = new TitleDTO("Testing");
        assertThat(titleDTO1).isNotNull().isInstanceOf(TitleDTO.class);
        assertThat(titleDTO1.getTodoTitle()).isEqualTo("Testing");
    }

    @Test
    void getTodoTitleDTOTest() {
        assertThat(titleDTO.getTodoTitle()).isEqualTo("Testing");
    }

    @Test
    void setTodoTitleDTOTest() {
        titleDTO.setTodoTitle("Testing 2");
        assertThat(titleDTO.getTodoTitle()).isEqualTo("Testing 2");
    }

    @Test
    void hashCodeTest() {
        TitleDTO titleDTO1 = new TitleDTO("test");
        TitleDTO titleDTO2 = new TitleDTO("test");
        assertThat(titleDTO1).hasSameHashCodeAs(titleDTO2);
    }

    @Test
    void toStringTest() {
        TitleDTO titleDTO1 = new TitleDTO("test");
        String toStringReturn = "TitleDTO [todoTitle=test]";
        assertThat(titleDTO1).hasToString(toStringReturn);
    }
}
