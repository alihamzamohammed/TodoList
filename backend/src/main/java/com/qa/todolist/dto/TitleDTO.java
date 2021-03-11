package com.qa.todolist.dto;

import java.util.Objects;

public class TitleDTO {
    
    private String todoTitle;


    public TitleDTO() {
    }


    public TitleDTO(String todoTitle) {
        this.todoTitle = todoTitle;
    }


    public String getTodoTitle() {
        return this.todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
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
        if (!(o instanceof TitleDTO)) {
            return false;
        }
        TitleDTO titleDTO = (TitleDTO) o;
        return Objects.equals(todoTitle, titleDTO.todoTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(todoTitle);
    }



}
