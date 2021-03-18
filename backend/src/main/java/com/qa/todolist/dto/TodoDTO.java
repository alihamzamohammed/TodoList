package com.qa.todolist.dto;

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
        return "TodoDTO [title=" + title + ", content=" + content + ", category=" + category + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TodoDTO))
            return false;
        TodoDTO other = (TodoDTO) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

}