package com.alta.bank.dto;

import lombok.Data;

@Data
public class PagingDto {

    private Integer page;
    private Integer size;
    private Long total;

}
