package com.qa.todolist.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.qa.todolist.data.model.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class CategoryDTOTest {

    CategoryDTO categoryDTO;
    List<TodoDTO> todos;

    @BeforeEach
    void setup() {
        categoryDTO = new CategoryDTO(1, "Testing");
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(CategoryDTO.class).withIgnoredFields("id")
                .withPrefabValues(Category.class, new Category("1"), new Category("2")).verify();
    }

    @Test
    void categoryDTOTest() {
        CategoryDTO categoryDTO1 = new CategoryDTO("Testing");
        assertThat(categoryDTO1).isNotNull().isInstanceOf(CategoryDTO.class);
        assertThat(categoryDTO1.getName()).isEqualTo("Testing");
    }

    @Test
    void categoryDTOWithIdTest() {
        CategoryDTO categoryDTO1 = new CategoryDTO(1, "Testing");
        assertThat(categoryDTO1).isNotNull().isInstanceOf(CategoryDTO.class);
        assertThat(categoryDTO1.getName()).isEqualTo("Testing");
        assertThat(categoryDTO1.getId()).isEqualTo(1);
    }

    @Test
    void categoryDTOWithTodoDTOsTest() {
        CategoryDTO categoryDTO1 = new CategoryDTO(1, "Testing");
        assertThat(categoryDTO1).isNotNull().isInstanceOf(CategoryDTO.class);
        assertThat(categoryDTO1.getName()).isEqualTo("Testing");
        assertThat(categoryDTO1.getId()).isEqualTo(1);
        assertThat(categoryDTO1.getTodos()).isEqualTo(todos);
    }

    @Test
    void getIdTest() {
        assertThat(categoryDTO.getId()).isEqualTo(1);
    }

    @Test
    void setIdTest() {
        categoryDTO.setId(2);
        assertThat(categoryDTO.getId()).isEqualTo(2);
    }

    @Test
    void getNameTest() {
        assertThat(categoryDTO.getName()).isEqualTo("Testing");
    }

    @Test
    void setNameTest() {
        categoryDTO.setName("Testing 2");
        assertThat(categoryDTO.getName()).isEqualTo("Testing 2");
    }

    @Test
    void getTodoDTOsTest() {
        assertThat(categoryDTO.getTodos()).isEqualTo(todos);
    }

    @Test
    void setTodoDTOsTest() {
        TitleDTO title = new TitleDTO("Testing");
        ContentDTO content = new ContentDTO("Testing");
        TodoDTO todo = new TodoDTO(1, title, content);
        List<TodoDTO> todos1 = List.of(todo);
        categoryDTO.setTodos(todos1);
        assertThat(categoryDTO.getTodos()).isEqualTo(todos1);
    }

    @Test
    void hashCodeTest() {
        CategoryDTO categoryDTO1 = new CategoryDTO("test");
        CategoryDTO categoryDTO2 = new CategoryDTO("test");
        assertThat(categoryDTO1).hasSameHashCodeAs(categoryDTO2);
    }

    @Test
    void toStringTest() {
        CategoryDTO categoryDTO1 = new CategoryDTO(1, "test");
        String toStringReturn = "CategoryDTO [name=test, todos=null]";
        assertThat(categoryDTO1).hasToString(toStringReturn);
    }

}
