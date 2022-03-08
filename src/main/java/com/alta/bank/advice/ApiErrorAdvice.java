package com.alta.bank.advice;

import javax.validation.ConstraintViolationException;

import com.alta.bank.exceptions.DatabaseException;
import com.alta.bank.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApiErrorAdvice {

    private static Logger logger = LoggerFactory.getLogger(ApiErrorAdvice.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handlerValidationArgumentType(MethodArgumentTypeMismatchException e) {
        logger.error("[HandlerValidationArgumentType]", e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlerNotFound(ResourceNotFoundException e) {
        logger.error("[HandlerNotFound]", e);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handlerContraintViolation(ConstraintViolationException e) {
        logger.error("[HandlerContraintViolation]", e);
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handlerDatabase(DatabaseException e) {
        logger.error("[HandlerDatabase]", e);
    }

}
