package com.qa.todolist.dto;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

public class TodoDTO {
    
    private int id;

    private TitleDTO title;

    private ContentDTO content;


    @Autowired
    public TodoDTO(int id, TitleDTO title, ContentDTO content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public TodoDTO(TitleDTO title, ContentDTO content) {
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TitleDTO getTitle() {
        return this.title;
    }

    public void setTitle(TitleDTO title) {
        this.title = title;
    }

    public ContentDTO getContent() {
        return this.content;
    }

    public void setContent(ContentDTO content) {
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
