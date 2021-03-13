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
        return "{" +
            " id='" + getId() + "'" +
            ", todoContent='" + getTodoContent() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Content)) {
            return false;
        }
        Content content = (Content) o;
        return id == content.id && Objects.equals(todoContent, content.todoContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, todoContent);
    }

}