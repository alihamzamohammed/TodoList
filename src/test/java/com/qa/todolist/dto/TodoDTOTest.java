package com.qa.todolist.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.qa.todolist.data.model.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class TodoDTOTest {

    static TitleDTO titleDTO;
    static ContentDTO contentDTO;
    static Category category;
    static TodoDTO todoDTO;

    @BeforeEach
    void setup() {
        titleDTO = new TitleDTO("Testing");
        contentDTO = new ContentDTO("Testing");
        category = new Category("Testing");
        todoDTO = new TodoDTO(1, titleDTO, contentDTO, category);
    }

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(TodoDTO.class).withIgnoredFields("id")
                .withPrefabValues(Category.class, new Category("1"), new Category("2")).verify();
    }

    @Test
    void todoDTONoIdTest() {
        TodoDTO todoDTO1 = new TodoDTO(titleDTO, contentDTO, category);
        assertThat(todoDTO1).isNotNull().isInstanceOf(TodoDTO.class);
        assertThat(todoDTO1.getTitle()).isEqualTo(titleDTO.getTodoTitle());
        assertThat(todoDTO1.getContent()).isEqualTo(contentDTO.getTodoContent());
        assertThat(todoDTO1.getCategory()).isEqualTo(category.getId());
    }

    @Test
    void todoDTOAllTest() {
        TodoDTO todoDTO1 = new TodoDTO(1, titleDTO, contentDTO, category);
        assertThat(todoDTO1).isNotNull().isInstanceOf(TodoDTO.class);
        assertThat(todoDTO1.getTitle()).isEqualTo(titleDTO.getTodoTitle());
        assertThat(todoDTO1.getContent()).isEqualTo(contentDTO.getTodoContent());
        assertThat(todoDTO1.getCategory()).isEqualTo(category.getId());
        assertThat(todoDTO1.getId()).isEqualTo(1);
    }

    @Test
    void todoDTOTest() {
        TodoDTO todoDTO1 = new TodoDTO(1, titleDTO, contentDTO);
        assertThat(todoDTO1).isNotNull().isInstanceOf(TodoDTO.class);
        assertThat(todoDTO1.getTitle()).isEqualTo(titleDTO.getTodoTitle());
        assertThat(todoDTO1.getContent()).isEqualTo(contentDTO.getTodoContent());
        assertThat(todoDTO1.getId()).isEqualTo(1);
    }

    @Test
    void todoDTOTitleContentTest() {
        TodoDTO todoDTO1 = new TodoDTO(titleDTO, contentDTO);
        assertThat(todoDTO1).isNotNull().isInstanceOf(TodoDTO.class);
        assertThat(todoDTO1.getTitle()).isEqualTo(titleDTO.getTodoTitle());
        assertThat(todoDTO1.getContent()).isEqualTo(contentDTO.getTodoContent());
    }

    @Test
    void getIdTest() {
        assertThat(todoDTO.getId()).isEqualTo(1);
    }

    @Test
    void setIdTest() {
        todoDTO.setId(2);
        assertThat(todoDTO.getId()).isEqualTo(2);
    }

    @Test
    void getCategoryTest() {
        assertThat(todoDTO.getCategory()).isEqualTo(category.getId());
    }

    @Test
    void setCategoryTest() {
        Category newCat = new Category("Testing 2");
        todoDTO.setCategory(newCat);
        assertThat(todoDTO.getCategory()).isEqualTo(newCat.getId());
    }

    @Test
    void getTitleDTOTest() {
        assertThat(todoDTO.getTitle()).isEqualTo(titleDTO.getTodoTitle());
    }

    @Test
    void setTitleTest() {
        TitleDTO newTitleDTO = new TitleDTO("Testing 2");
        todoDTO.setTitle(newTitleDTO);
        assertThat(todoDTO.getTitle()).isEqualTo(newTitleDTO.getTodoTitle());
    }

    @Test
    void getContentTest() {
        assertThat(todoDTO.getContent()).isEqualTo(contentDTO.getTodoContent());
    }

    @Test
    void setContentTest() {
        ContentDTO newContentDTO = new ContentDTO("Testing 2");
        todoDTO.setContent(newContentDTO);
        assertThat(todoDTO.getContent()).isEqualTo(newContentDTO.getTodoContent());
    }

    @Test
    void hashCodeTest() {
        TodoDTO todoDTO1 = new TodoDTO(titleDTO, contentDTO, category);
        TodoDTO todoDTO2 = new TodoDTO(titleDTO, contentDTO, category);
        assertThat(todoDTO1).hasSameHashCodeAs(todoDTO2);
    }

    @Test
    void toStringTest() {
        assertThat(todoDTO).hasToString(
                "TodoDTO [title=TitleDTO [todoTitle=Testing], content=ContentDTO [todoContent=Testing], category=Category [name=Testing, todos=null]]");
    }

}
