package com.alta.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    protected Long id;
    protected String email;
    protected String firstName;
    protected String lastName;

}
