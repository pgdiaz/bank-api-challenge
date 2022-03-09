package com.alta.bank.advice.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiFieldError {
    
    private String field;
    private String message;

}
