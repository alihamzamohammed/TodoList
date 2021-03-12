package com.qa.todolist.dto;

import java.util.List;
import java.util.Objects;

import com.qa.todolist.data.model.Todo;

public class CategoryDTO {
    
    private int id;

    private String name;

    private List<Todo> todos;


    public CategoryDTO() {
    }

    public CategoryDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Todo> getTodos() {
        return this.todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CategoryDTO)) {
            return false;
        }
        CategoryDTO categoryDTO = (CategoryDTO) o;
        return id == categoryDTO.id && Objects.equals(name, categoryDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }

}
