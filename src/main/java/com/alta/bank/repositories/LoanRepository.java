package com.alta.bank.repositories;

import com.alta.bank.dto.LoanDto;
import com.alta.bank.dto.PaginationDto;

public interface LoanRepository {

    public PaginationDto<LoanDto> findByUserId(Long userId, Integer page, Integer size);

    public PaginationDto<LoanDto> getAll(Integer page, Integer size);

}
