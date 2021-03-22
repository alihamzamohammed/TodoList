package com.qa.todolist.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Todo item wasn't found")
public class CategoryNotFoundException extends EntityNotFoundException {

    /**
     *
     */
    private static final long serialVersionUID = 6743074521168496360L;

    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
