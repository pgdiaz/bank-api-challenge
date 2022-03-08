package com.alta.bank.infrastructure;

import com.alta.bank.dto.CreateUserDto;
import com.alta.bank.dto.UserDto;
import com.alta.bank.dto.UserLoanDto;

public interface UserRepository {

    public UserLoanDto findById(Long id);

    public UserDto save(CreateUserDto user);

    public void deleteById(Long id);

}
