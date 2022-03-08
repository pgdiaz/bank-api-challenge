package com.alta.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagingDto {

    private Integer page;
    private Integer size;
    private Long total;

}
