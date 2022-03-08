package com.alta.bank.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import com.alta.bank.dto.LoanDto;
import com.alta.bank.dto.PaginationDto;
import com.alta.bank.infrastructure.LoanRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Loans")
@Validated
@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanRepository repository;

    public LoanController(LoanRepository loanRepository) {
        this.repository = loanRepository;
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PaginationDto<LoanDto> search(
        @RequestParam(value = "user_id", required = false) @Min(1) Long userId,
        @RequestParam @Min(1) Integer page,
        @RequestParam @Min(1) Integer size) {

        return Optional.ofNullable(userId).isPresent() ?
            this.repository.findByUserId(userId, page, size) :
            this.repository.getAll(page, size);
    }

}
