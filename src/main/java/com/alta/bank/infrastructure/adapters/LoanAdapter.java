package com.alta.bank.infrastructure.adapters;

import java.util.List;
import java.util.stream.Collectors;

import com.alta.bank.dto.LoanDto;
import com.alta.bank.dto.PaginationDto;
import com.alta.bank.dto.PagingDto;
import com.alta.bank.exceptions.DatabaseException;
import com.alta.bank.exceptions.ResourceNotFoundException;
import com.alta.bank.infrastructure.LoanRepository;
import com.alta.bank.infrastructure.entities.LoanEntity;
import com.alta.bank.infrastructure.entities.UserEntity;
import com.alta.bank.infrastructure.repositories.LoanJpaRepository;
import com.alta.bank.infrastructure.repositories.UserJpaRepository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class LoanAdapter implements LoanRepository {

    private final LoanJpaRepository repository;
    private final UserJpaRepository userRepository;

    public LoanAdapter(
        LoanJpaRepository loanJpaRepository,
        UserJpaRepository userJpaRepository) {

        this.repository = loanJpaRepository;
        this.userRepository = userJpaRepository;
    }

    @Override
    public PaginationDto<LoanDto> findByUserId(Long userId, Integer page, Integer size) {

        Page<LoanEntity> result;
        try {
            result = this.repository
                .findAllByUserId(userId, PageRequest.of(page-1, size));
        } catch (DataAccessException e) {
            throw new DatabaseException();
        }

        List<LoanDto> loans = result
            .stream()
            .map(l -> new LoanDto(l.getId(), l.getTotal(), l.getUser().getId()))
            .collect(Collectors.toList());

        PagingDto paging = new PagingDto(
            result.getNumber()+1, result.getSize(), result.getTotalElements());

        return new PaginationDto<LoanDto>(loans, paging);
    }

    @Override
    public PaginationDto<LoanDto> getAll(Integer page, Integer size) {

        Page<LoanEntity> result;
        try {
            result = this.repository.findAll(PageRequest.of(page-1, size));
        } catch (DataAccessException e) {
            throw new DatabaseException();
        }

        List<LoanDto> loans = result.stream()
            .map(l -> new LoanDto(l.getId(), l.getTotal(), l.getUser().getId()))
            .collect(Collectors.toList());

        PagingDto paging = new PagingDto(
            result.getNumber()+1, result.getSize(), result.getTotalElements());

        return new PaginationDto<LoanDto>(loans, paging);
    }

    @Override
    public void save(LoanDto loan) {

        try {
            UserEntity user = this.userRepository
                .findById(loan.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException());

            LoanEntity entity = new LoanEntity();
            entity.setTotal(loan.getTotal());
            entity.setUser(user);

            this.repository.save(entity);
        } catch (DataAccessException e) {
            throw new DatabaseException();
        }
    }

}
