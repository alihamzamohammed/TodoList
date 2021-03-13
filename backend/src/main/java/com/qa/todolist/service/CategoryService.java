package com.qa.todolist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.qa.todolist.data.model.Category;
import com.qa.todolist.data.repository.CategoryRepository;
import com.qa.todolist.dto.CategoryDTO;
import com.qa.todolist.exceptions.CategoryAlreadyExistsException;
import com.qa.todolist.exceptions.CategoryNotFoundExcepion;
import com.qa.todolist.mapper.CategoryMapper;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> readAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        categories.forEach(cat -> categoryDTOs.add(categoryMapper.mapToDTO(cat)));
        return categoryDTOs;
    }

    public CategoryDTO readById(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return categoryMapper.mapToDTO(category.get());
        } else {
            throw new CategoryNotFoundExcepion();
        }
    }

    public CategoryDTO createCategory(Category category) {
        if (!categoryRepository.existsByName(category.getName())) {
            return categoryMapper.mapToDTO(categoryRepository.save(category));
        } else {
            throw new CategoryAlreadyExistsException();
        }
    }

    public CategoryDTO updateCategory(int id, Category category) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category catToUpdate = optional.get();
            catToUpdate.setName(category.getName());
            catToUpdate.setTodos(category.getTodos());
            return categoryMapper.mapToDTO(categoryRepository.save(catToUpdate));
        } else {
            throw new CategoryNotFoundExcepion();
        }
    }

    public Boolean deleteCategory(int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundExcepion();
        }
        return !categoryRepository.existsById(id);
    }
}
