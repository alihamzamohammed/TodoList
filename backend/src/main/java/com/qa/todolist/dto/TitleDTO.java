package com.qa.todolist.dto;

import java.util.Objects;

public class TitleDTO {
    

    private int id;

    private String todoTitle;


    public TitleDTO() {
    }

    public TitleDTO(int id, String todoTitle) {
        this.id = id;
        this.todoTitle = todoTitle;
    }

    public TitleDTO(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
            " id='" + getId() + "'" +
            ", todoTitle='" + getTodoTitle() + "'" +
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
        return id == titleDTO.id && Objects.equals(todoTitle, titleDTO.todoTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, todoTitle);
    }

}
