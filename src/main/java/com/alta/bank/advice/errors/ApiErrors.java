package com.alta.bank.advice.errors;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiErrors {

    public ApiErrors(HttpStatus httpStatus, List<ApiFieldError> errors) {
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.errors = errors;
    }

    private Integer status;
    private String error;
    private List<ApiFieldError> errors;

}
