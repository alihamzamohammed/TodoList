package com.qa.todolist.data.model;

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

    @OneToOne(targetEntity = Todo.class, fetch = FetchType.LAZY)
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


}