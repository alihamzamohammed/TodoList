package com.qa.todolist.data.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fk_title_id")
    private Title title;

    @OneToOne(fetch = FetchType.EAGER, 
        cascade = {CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "fk_content_id")
    private Content content;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_category_id")
    private Category category;


    public Todo() {
    }

    public Todo(int id, Title title, Content content, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    // public Todo(Title title, Content content) {
    //     this.title = title;
    //     this.content = content;
    // }

    public Todo(Title title, Content content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
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


    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo todo = (Todo) o;
        return id == todo.id && Objects.equals(title, todo.title) && Objects.equals(content, todo.content) && Objects.equals(category, todo.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, category);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
    

}
