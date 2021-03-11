package com.qa.todolist.mapper;

import com.qa.todolist.data.model.Content;
import com.qa.todolist.dto.ContentDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentMapper {
    
    private ModelMapper modelMapper;

    @Autowired
    public ContentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ContentDTO mapToDTO(Content content) {
        return this.modelMapper.map(content, ContentDTO.class);
    }

    public Content mapToContent(ContentDTO contentDTO) {
        return this.modelMapper.map(contentDTO, Content.class);
    }
}
