package com.alta.bank.dto;

import java.util.List;

public class UserLoanDto extends UserDto {

    public UserLoanDto() {
        super();
    }

    public UserLoanDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        List<LoanDto> loans) {

        super(id, email, firstName, lastName);
        this.loans = loans;
    }

    private List<LoanDto> loans;

    public List<LoanDto> getLoans() {
        return this.loans;
    }

}
