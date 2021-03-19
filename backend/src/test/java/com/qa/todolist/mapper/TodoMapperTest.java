package com.qa.todolist.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;
import com.qa.todolist.data.model.Todo;
import com.qa.todolist.dto.CategoryDTO;
import com.qa.todolist.dto.ContentDTO;
import com.qa.todolist.dto.TitleDTO;
import com.qa.todolist.dto.TodoDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoMapperTest {

    @Autowired
    TodoMapper todoMapper;
    Title title;
    Category category;
    Content content;
    TitleDTO titleDTO;
    CategoryDTO categoryDTO;
    ContentDTO contentDTO;
    Todo todo;
    TodoDTO todoDTO;

    @BeforeEach
    void setup() {
        title = new Title("test");
        content = new Content("test");
        category = new Category("test");
        titleDTO = new TitleDTO("test");
        contentDTO = new ContentDTO("test");
        todo = new Todo(title, content, null);
        todoDTO = new TodoDTO(titleDTO, contentDTO, null);
    }

    @Test
    void mapToDTOTest() {
        assertThat(todoMapper.mapToDTO(todo)).isEqualTo(todoDTO);
    }

    @Test
    void mapToTodoTest() {
        assertThat(todoMapper.mapToTodo(todoDTO)).isEqualTo(todo);
    }
}
