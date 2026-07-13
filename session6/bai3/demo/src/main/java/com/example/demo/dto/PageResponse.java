package com.example.demo.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> items;
    private Integer page;
    private Integer size;
    private Long totalItems;
    private Integer totalPages;
    private Boolean isLast;
}