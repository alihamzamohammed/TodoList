package com.qa.todolist.mapper;

import com.qa.todolist.data.model.Title;
import com.qa.todolist.dto.TitleDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TitleMapper {
    
    private ModelMapper modelMapper;

    @Autowired
    public TitleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TitleDTO mapToDTO(Title title) {
        return this.modelMapper.map(title, TitleDTO.class);
    }

    public Title mapToTitle(TitleDTO titleDTO) {
        return this.modelMapper.map(titleDTO, Title.class);
    }
}
