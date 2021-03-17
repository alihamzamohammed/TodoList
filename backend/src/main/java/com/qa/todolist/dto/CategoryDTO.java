package com.qa.todolist.dto;

import java.util.List;
import java.util.Objects;

public class CategoryDTO {

    private int id;

    private String name;

    private List<TodoDTO> todos;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
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

    public List<TodoDTO> getTodos() {
        return this.todos;
    }

    public void setTodos(List<TodoDTO> todos) {
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
        return id == categoryDTO.id && Objects.equals(name, categoryDTO.name)
                && Objects.equals(todos, categoryDTO.todos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, todos);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + "}";
    }

}
