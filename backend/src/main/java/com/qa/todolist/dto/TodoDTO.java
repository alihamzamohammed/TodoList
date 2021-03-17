package com.qa.todolist.dto;

import java.util.Objects;

import com.qa.todolist.data.model.Category;

import org.springframework.beans.factory.annotation.Autowired;

public class TodoDTO {

    private int id;

    private TitleDTO title;

    private ContentDTO content;

    private Category category;

    public Integer getCategory() {
        return this.category.getId();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TodoDTO() {
    }

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

    public TodoDTO(TitleDTO title, ContentDTO content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public TodoDTO(int id, TitleDTO title, ContentDTO content, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title.getTodoTitle();
    }

    public void setTitle(TitleDTO title) {
        this.title = title;
    }

    public String getContent() {
        return this.content.getTodoContent();
    }

    public void setContent(ContentDTO content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "{" + " title='" + getTitle() + "'" + ", content='" + getContent() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TodoDTO)) {
            return false;
        }
        TodoDTO todoDTO = (TodoDTO) o;
        return id == todoDTO.id && Objects.equals(title, todoDTO.title) && Objects.equals(content, todoDTO.content)
                && Objects.equals(category, todoDTO.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, category);
    }

}