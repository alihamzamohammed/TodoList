package com.qa.todolist.mapper;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.dto.CategoryDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    
    private ModelMapper modelMapper;

    @Autowired
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryDTO mapToDTO(Category category) {
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    public Category mapToCategory(CategoryDTO categoryDTO) {
        return this.modelMapper.map(categoryDTO, Category.class);
    }
}
