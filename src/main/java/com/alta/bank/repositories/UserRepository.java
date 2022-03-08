package com.alta.bank.repositories;

import com.alta.bank.dto.CreateUserDto;
import com.alta.bank.dto.UserDto;

public interface UserRepository {

    public UserDto findById(Long id);

    public UserDto save(CreateUserDto user);

    public void deleteById(Long id);

}
