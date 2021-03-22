package com.qa.todolist.dto;

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
        return "ContentDTO [todoContent=" + todoContent + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((todoContent == null) ? 0 : todoContent.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContentDTO other = (ContentDTO) obj;
        if (todoContent == null) {
            if (other.todoContent != null)
                return false;
        } else if (!todoContent.equals(other.todoContent))
            return false;
        return true;
    }

}