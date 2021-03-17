package com.qa.todolist.mapper;

import com.qa.todolist.data.model.Content;
import com.qa.todolist.dto.ContentDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ContentMapperTest {

    @Autowired
    ContentMapper contentMapper;
    Content content;
    ContentDTO contentDTO;

    @BeforeEach
    void setup() {
        content = new Content("content");
        contentDTO = new ContentDTO("content");
    }

    @Test
    void mapToDTOTest() {
        assertThat(contentMapper.mapToDTO(content)).isEqualTo(contentDTO);
    }

    @Test
    void mapToContentTest() {
        assertThat(contentMapper.mapToContent(contentDTO)).isEqualTo(content);
    }
}
