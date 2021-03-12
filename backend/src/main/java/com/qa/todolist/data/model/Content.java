package com.qa.todolist.data.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private int id;

    @Column(name = "content")
    private String todoContent;

    @OneToOne(targetEntity = Todo.class, fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "fk_todo_id")
    private Todo todo;

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

    public Todo getTodo() {
        return this.todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }


    public Content() {
    }

    public Content(int id, String todoContent, Todo todo) {
        this.id = id;
        this.todoContent = todoContent;
        this.todo = todo;
    }
    public Content(String todoContent, Todo todo) {
        this.todoContent = todoContent;
        this.todo = todo;
    }
    public Content(String todoContent) {
        this.todoContent = todoContent;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", todoContent='" + getTodoContent() + "'" +
            ", todo='" + getTodo() + "'" +
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
        return id == content.id && Objects.equals(todoContent, content.todoContent) && Objects.equals(todo, content.todo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, todoContent, todo);
    }

}