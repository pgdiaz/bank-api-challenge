package com.alta.bank.infrastructure.repositories;

import com.alta.bank.infrastructure.entities.LoanEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanJpaRepository extends PagingAndSortingRepository<LoanEntity, Long> {

    Page<LoanEntity> findAllByUserId(Long userId, Pageable pageable);

}
