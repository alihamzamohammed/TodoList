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
        return "{" +
            " todoTitle='" + getTodoTitle() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Title)) {
            return false;
        }
        Title title = (Title) o;
        return Objects.equals(todoTitle, title.todoTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(todoTitle);
    }


}
