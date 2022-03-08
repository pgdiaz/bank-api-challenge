package com.alta.bank.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LoanDto {

    private Long id;
    private BigDecimal total;
    private Long userId;

}
