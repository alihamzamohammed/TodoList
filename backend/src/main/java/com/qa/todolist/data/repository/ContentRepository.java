package com.qa.todolist.data.repository;

import com.qa.todolist.data.model.Content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer>{
    
}
