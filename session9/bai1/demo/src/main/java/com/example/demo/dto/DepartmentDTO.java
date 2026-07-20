package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDTO {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @Size(max = 100, message = "Description must be at most 100 characters")
    private String description;
}
