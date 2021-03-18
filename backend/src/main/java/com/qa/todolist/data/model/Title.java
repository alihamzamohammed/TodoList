package com.qa.todolist.data.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id")
    private int id;

    @Column(name = "title")
    private String todoTitle;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodoTitle() {
        return this.todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public Title() {
    }

    public Title(int id, String todoTitle) {
        this.id = id;
        this.todoTitle = todoTitle;
    }

    public Title(String todoTitle) {
        this.todoTitle = todoTitle;
    }

	@Override
	public String toString() {
		return "Title [todoTitle=" + todoTitle + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		result = prime * result + id;
		result = prime * result + ((todoTitle == null) ? 0 : todoTitle.hashCode());
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
		Title other = (Title) obj;
		if (todoTitle == null) {
			if (other.todoTitle != null)
				return false;
		} else if (!todoTitle.equals(other.todoTitle))
			return false;
		return true;
	}

    

}
