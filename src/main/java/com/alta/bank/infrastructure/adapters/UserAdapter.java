package com.alta.bank.infrastructure.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.alta.bank.dto.CreateUserDto;
import com.alta.bank.dto.LoanDto;
import com.alta.bank.dto.UserDto;
import com.alta.bank.dto.UserLoanDto;
import com.alta.bank.exceptions.DatabaseException;
import com.alta.bank.exceptions.ResourceNotFoundException;
import com.alta.bank.infrastructure.UserRepository;
import com.alta.bank.infrastructure.entities.UserEntity;
import com.alta.bank.infrastructure.repositories.UserJpaRepository;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class UserAdapter implements UserRepository {

    private final UserJpaRepository repository;

    public UserAdapter(UserJpaRepository userJpaRepository) {
        this.repository = userJpaRepository;
    }

    @Override
    public UserLoanDto findById(Long id) {

        UserEntity entity;
        try {
            entity = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        } catch (DataAccessException e) {
            throw new DatabaseException();
        }

        List<LoanDto> loans = entity.getLoans().stream()
            .map(l -> new LoanDto(l.getId(), l.getTotal(), l.getUser().getId()))
            .collect(Collectors.toList());

        return new UserLoanDto(
            entity.getId(),
            entity.getEmail(),
            entity.getFirstName(),
            entity.getLastName(),
            loans);
    }

    @Override
    public UserDto save(CreateUserDto user) {

        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());

        try {
            this.repository.save(entity);
        } catch (DataAccessException e) {
            throw new DatabaseException();
        }

        return new UserDto(
            entity.getId(),
            entity.getEmail(),
            entity.getFirstName(),
            entity.getLastName());
    }

    @Override
    public void deleteById(Long id) {
        try {
            this.repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException();
        } catch (DataAccessException e) {
            throw new DatabaseException();
        }
    }

}
