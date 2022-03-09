package com.alta.bank.advice.errors;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {

    public ApiError(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.error = httpStatus.name();
        this.message = message;
    }

    public ApiError(HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.error = httpStatus.name();
    }

    private Integer status;
    private String error;
    private String message;

}
