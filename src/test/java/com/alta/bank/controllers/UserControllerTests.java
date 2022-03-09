package com.alta.bank.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alta.bank.dto.CreateUserDto;
import com.alta.bank.dto.UserLoanDto;
import com.alta.bank.exceptions.ResourceNotFoundException;
import com.alta.bank.infrastructure.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repository;

    @Test
    public void getSuccessfully() throws Exception {

        when(this.repository.findById(anyLong())).thenReturn(new UserLoanDto());

        this.mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(1)).findById(eq(1L));
    }

    @Test
    public void getInvalidId() throws Exception {

        this.mockMvc.perform(get("/users/a"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(0)).findById(anyLong());
    }

    @Test
    public void getInvalidMinId() throws Exception {

        this.mockMvc.perform(get("/users/-1"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(0)).findById(anyLong());
    }

    @Test
    public void getNotFound() throws Exception {

        when(this.repository.findById(anyLong())).thenThrow(new ResourceNotFoundException());

        this.mockMvc.perform(get("/users/9999999"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(1)).findById(eq(9999999L));
    }

    @Test
    public void createSuccessfully() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"test@alta.com\", \"firstName\":\"Pepe\", \"lastName\":\"Argento\"}");

        when(this.repository.save(any(CreateUserDto.class))).thenReturn(new UserLoanDto());

        this.mockMvc.perform(request)
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(1)).save(any(CreateUserDto.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "{\"email\":\"test\", \"firstName\":\"Pepe\", \"lastName\":\"Argento\"}",
        "{\"email\":\"test@alta.com\", \"firstName\":\"Pepe\"}",
        "{\"email\":\"test@alta.com\", \"lastName\":\"Argento\"}" })
    public void createInvalidRequest(String input) throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(input);

        this.mockMvc.perform(request)
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(0)).save(any(CreateUserDto.class));
    }

    @Test
    public void removeSuccessfully() throws Exception {

        this.mockMvc.perform(delete("/users/1"))
            .andExpect(status().isNoContent());

        verify(this.repository, times(1)).deleteById(eq(1L));
    }

    @Test
    public void removeInvalidId() throws Exception {

        this.mockMvc.perform(delete("/users/a"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(0)).deleteById(anyLong());
    }

    @Test
    public void removeInvalidMinId() throws Exception {

        this.mockMvc.perform(delete("/users/-1"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(0)).deleteById(anyLong());
    }

    @Test
    public void removeNotFound() throws Exception {

        doThrow(new ResourceNotFoundException()).when(this.repository).deleteById(anyLong());

        this.mockMvc.perform(delete("/users/9999999"))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(this.repository, times(1)).deleteById(eq(9999999L));
    }

}
