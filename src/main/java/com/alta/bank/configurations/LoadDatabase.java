package com.alta.bank.configurations;

import java.math.BigDecimal;

import com.alta.bank.dto.CreateUserDto;
import com.alta.bank.dto.LoanDto;
import com.alta.bank.dto.UserDto;
import com.alta.bank.infrastructure.LoanRepository;
import com.alta.bank.infrastructure.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    public CommandLineRunner initDatabase(
        UserRepository userRepository,
        LoanRepository loanRepository) {

        return args -> {
            CreateUserDto firstUser = new CreateUserDto();
            firstUser.setEmail("test@app.com.ar");
            firstUser.setFirstName("Pepe");
            firstUser.setLastName("Argento");
            UserDto first = userRepository.save(firstUser);
            LoanDto firstUserLoan = new LoanDto();
            firstUserLoan.setTotal(new BigDecimal(2500));
            firstUserLoan.setUserId(first.getId());
            loanRepository.save(firstUserLoan);

            CreateUserDto secondUser = new CreateUserDto();
            secondUser.setEmail("jose.perez@app.com.ar");
            secondUser.setFirstName("Jose");
            secondUser.setLastName("Perez");
            UserDto second = userRepository.save(secondUser);
            LoanDto secondUserFirtsLoan = new LoanDto();
            secondUserFirtsLoan.setTotal(new BigDecimal(10000));
            secondUserFirtsLoan.setUserId(second.getId());
            loanRepository.save(secondUserFirtsLoan);
            LoanDto secondUserSecondLoan = new LoanDto();
            secondUserSecondLoan.setTotal(new BigDecimal(5600));
            secondUserSecondLoan.setUserId(second.getId());
            loanRepository.save(secondUserSecondLoan);
        };
    }

}
