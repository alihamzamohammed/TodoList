package com.qa.todolist.data.model;

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


}
