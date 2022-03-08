package com.alta.bank.repositories;

import com.alta.bank.dto.CreateUserDto;
import com.alta.bank.dto.UserDto;

import org.springframework.stereotype.Repository;

@Repository
public class UserAdapter implements UserRepository {

    @Override
    public UserDto findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDto save(CreateUserDto user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
    }

}
