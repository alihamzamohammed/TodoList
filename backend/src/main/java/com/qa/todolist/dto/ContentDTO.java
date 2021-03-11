package com.qa.todolist.dto;

import java.util.Objects;

public class ContentDTO { 

    private String todoContent;

    public ContentDTO() {
    }

    public ContentDTO(String todoContent) {
        this.todoContent = todoContent;
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
            " todoContent='" + getTodoContent() + "'" +
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
        return Objects.equals(todoContent, contentDTO.todoContent);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(todoContent);
    }
    
}