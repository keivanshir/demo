package com.example.demo2.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(exception = NotFoundException.class)
    public String notFoundException(NotFoundException exception){
        return exception.getMessage();
    }
}
