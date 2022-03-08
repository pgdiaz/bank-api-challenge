package com.alta.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserDto {

    @Email
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

}
