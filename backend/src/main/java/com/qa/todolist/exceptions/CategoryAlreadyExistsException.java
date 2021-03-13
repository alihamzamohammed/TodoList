package com.qa.todolist.exceptions;

import javax.persistence.EntityExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Category already exists")
public class CategoryAlreadyExistsException extends EntityExistsException {

    /**
     *
     */
    private static final long serialVersionUID = -8860561075322143503L;

    public CategoryAlreadyExistsException() {
        super();
    }

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
