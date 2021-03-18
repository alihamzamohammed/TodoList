package com.qa.todolist.mapper;

import com.qa.todolist.data.model.Title;
import com.qa.todolist.dto.TitleDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TitleMapperTest {

    @Autowired
    TitleMapper titleMapper;
    Title title;
    TitleDTO titleDTO;

    @BeforeEach
    void setup() {
        title = new Title("title");
        titleDTO = new TitleDTO("title");
    }

    @Test
    void mapToDTOTest() {
        assertThat(titleMapper.mapToDTO(title)).isEqualTo(titleDTO);
    }

    @Test
    void mapToTitleTest() {
        assertThat(titleMapper.mapToTitle(titleDTO)).isEqualTo(title);
    }
}
