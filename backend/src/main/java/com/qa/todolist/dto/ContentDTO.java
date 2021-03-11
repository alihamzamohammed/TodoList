package com.qa.todolist.dto;

import java.util.Objects;

public class ContentDTO { 
   
    private int id;

    private String todoContent;


    public ContentDTO() {
    }

    public ContentDTO(int id, String todoContent) {
        this.id = id;
        this.todoContent = todoContent;
    }

    public ContentDTO(String todoContent) {
        this.todoContent = todoContent;
    }

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
        if (!(o instanceof ContentDTO)) {
            return false;
        }
        ContentDTO contentDTO = (ContentDTO) o;
        return id == contentDTO.id && Objects.equals(todoContent, contentDTO.todoContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, todoContent);
    }

}
