package com.qa.todolist.data.repository;

import com.qa.todolist.data.model.Title;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
    
}
