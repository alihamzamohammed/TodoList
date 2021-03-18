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
	public String toString() {
		return "CategoryDTO [name=" + name + ", todos=" + todos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((todos == null) ? 0 : todos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (todos == null) {
			if (other.todos != null)
				return false;
		} else if (!todos.equals(other.todos))
			return false;
		return true;
	}

	
}
