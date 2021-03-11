package com.qa.todolist.data.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "todo", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Title title;

    @OneToOne(mappedBy = "todo", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Content content;

    public Todo() {
    }

    public Todo(int id, Title title, Content content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public Todo(Title title, Content content) {
        this.title = title;
        this.content = content;
    }


    public Title getTitle() {
        return this.title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Content getContent() {
        return this.content;
    }

    public void setContent(Content content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo todo = (Todo) o;
        return id == todo.id && Objects.equals(title, todo.title) && Objects.equals(content, todo.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content);
    }

}
