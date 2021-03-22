package com.qa.todolist.data.repository;

import com.qa.todolist.data.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public boolean existsByName(String name);

}
