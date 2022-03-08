package com.alta.bank.controllers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alta.bank.dto.LoanDto;
import com.alta.bank.dto.PaginationDto;
import com.alta.bank.repositories.LoanRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LoanController.class)
public class LoanControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanRepository repository;

    @Test
    public void searchSuccessfully() throws Exception {

        when(this.repository.findByUserId(anyLong(), anyInt(), anyInt()))
            .thenReturn(new PaginationDto<LoanDto>());

        this.mockMvc.perform(get("/loans/search?user_id=1&page=1&size=5"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(1)).findByUserId(eq(1L), eq(1), eq(5));
    }

    @Test
    public void searchWithoutUserIdSuccessfully() throws Exception {

        when(this.repository.getAll(anyInt(), anyInt()))
            .thenReturn(new PaginationDto<LoanDto>());

        this.mockMvc.perform(get("/loans/search?page=1&size=5"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(1)).getAll(eq(1), eq(5));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "/loans/search?user_id=0&page=1&size=5",
        "/loans/search?size=5",
        "/loans/search?page=1",
        "/loans/search?page=0&size=5",
        "/loans/search?page=1&size=0" })
    public void searchInvalidRequest(String input) throws Exception {

        this.mockMvc.perform(get(input))
            .andExpect(status().isBadRequest());

        verify(this.repository, times(0)).findByUserId(anyLong(), anyInt(), anyInt());
        verify(this.repository, times(0)).getAll(anyInt(), anyInt());
    }

}
