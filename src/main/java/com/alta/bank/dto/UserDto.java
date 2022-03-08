package com.alta.bank.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<LoanDto> loans;

}
