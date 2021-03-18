package com.qa.todolist.data.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private int id;

    @Column(name = "content")
    private String todoContent;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTodoContent() {
        return this.todoContent;
    }

    public void setTodoContent(String todoContent) {
        this.todoContent = todoContent;
    }

    public Content() {
    }

    public Content(int id, String todoContent) {
        this.id = id;
        this.todoContent = todoContent;
    }

    public Content(String todoContent) {
        this.todoContent = todoContent;
    }

	@Override
	public String toString() {
		return "Content [id=" + id + ", todoContent=" + todoContent + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((todoContent == null) ? 0 : todoContent.hashCode());
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
		Content other = (Content) obj;
		if (id != other.id)
			return false;
		if (todoContent == null) {
			if (other.todoContent != null)
				return false;
		} else if (!todoContent.equals(other.todoContent))
			return false;
		return true;
	}

    

}