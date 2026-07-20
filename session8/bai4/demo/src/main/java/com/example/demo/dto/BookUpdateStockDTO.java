package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookUpdateStockDTO {
    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock must be at least 0")
    private Integer stock;
}
