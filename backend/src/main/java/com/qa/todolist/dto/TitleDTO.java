package com.qa.todolist.dto;

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
		return "TitleDTO [todoTitle=" + todoTitle + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((todoTitle == null) ? 0 : todoTitle.hashCode());
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
		TitleDTO other = (TitleDTO) obj;
		if (todoTitle == null) {
			if (other.todoTitle != null)
				return false;
		} else if (!todoTitle.equals(other.todoTitle))
			return false;
		return true;
	}

}
