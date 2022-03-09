package com.alta.bank.advice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolationException;

import com.alta.bank.advice.errors.ApiError;
import com.alta.bank.advice.errors.ApiErrors;
import com.alta.bank.advice.errors.ApiFieldError;
import com.alta.bank.exceptions.DatabaseException;
import com.alta.bank.exceptions.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApiErrorAdvice {

    private static Logger logger = LoggerFactory.getLogger(ApiErrorAdvice.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerValidationArgumentType(MethodArgumentTypeMismatchException e) {
        logger.error("[HandlerValidationArgumentType]", e);

        return new ApiError(HttpStatus.BAD_REQUEST, "Invalid argument type");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerValidationArgument(MethodArgumentNotValidException e) {
        logger.error("[HandlerValidationArgument]", e);

        List<ApiFieldError> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(err -> {
            errors.add(new ApiFieldError(err.getField(), err.getDefaultMessage()));
        });

        return new ApiErrors(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerMissingParameter(MissingServletRequestParameterException e) {
        logger.error("[HandlerMissingParameter]", e);

        return new ApiErrors(
            HttpStatus.BAD_REQUEST,
            Collections.singletonList(new ApiFieldError(e.getParameterName(), e.getMessage())));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerContraintViolation(ConstraintViolationException e) {
        logger.error("[HandlerContraintViolation]", e);

        List<ApiFieldError> errors = new ArrayList<>();
        e.getConstraintViolations().forEach(err -> {
            errors.add(new ApiFieldError(err.getPropertyPath().toString(), err.getMessage()));
        });

        return new ApiErrors(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handlerNotFound(ResourceNotFoundException e) {
        logger.error("[HandlerNotFound]", e);

        return new ApiError(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handlerDatabase(DatabaseException e) {
        logger.error("[HandlerDatabase]", e);

        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
