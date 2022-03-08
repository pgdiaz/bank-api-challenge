package com.alta.bank.repositories;

import com.alta.bank.dto.LoanDto;
import com.alta.bank.dto.PaginationDto;

import org.springframework.stereotype.Repository;

@Repository
public class LoanAdapter implements LoanRepository {

    @Override
    public PaginationDto<LoanDto> findByUserId(Long userId, Integer page, Integer size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PaginationDto<LoanDto> getAll(Integer page, Integer size) {
        // TODO Auto-generated method stub
        return null;
    }

}
