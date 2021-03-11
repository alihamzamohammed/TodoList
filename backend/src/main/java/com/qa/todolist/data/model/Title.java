package com.qa.todolist.data.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id")
    private int id;

    @Column(name = "title")
    private String todoTitle;

    @OneToOne(targetEntity = Todo.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_todo_id")
    private Todo todo;

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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Title)) {
            return false;
        }
        Title title = (Title) o;
        return id == title.id && Objects.equals(todoTitle, title.todoTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, todoTitle);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", todoTitle='" + getTodoTitle() + "'" + "}";
    }

}
