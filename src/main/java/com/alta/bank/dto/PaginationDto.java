package com.alta.bank.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaginationDto<T> {

    private List<T> items;
    private PagingDto paging;

}
