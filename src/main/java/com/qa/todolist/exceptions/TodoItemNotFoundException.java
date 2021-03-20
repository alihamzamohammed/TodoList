package com.qa.todolist.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Todo item wasn't found")
public class TodoItemNotFoundException extends EntityNotFoundException{

    /**
     *
     */
    private static final long serialVersionUID = -1424141744361410384L;


    public TodoItemNotFoundException() {
        super();
    }
    
    public TodoItemNotFoundException(String message) {
        super(message);
    }
}
