package com.qa.todolist.dto;

import java.util.Objects;

import com.qa.todolist.data.model.Content;
import com.qa.todolist.data.model.Title;

public class TodoDTO {
    
    private int id;

    private Title title;

    private Content content;


    public TodoDTO() {
    }

    public TodoDTO(int id, Title title, Content content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public TodoDTO(Title title, Content content) {
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
            " title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TodoDTO)) {
            return false;
        }
        TodoDTO todoDTO = (TodoDTO) o;
        return Objects.equals(title, todoDTO.title) && Objects.equals(content, todoDTO.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }


}
