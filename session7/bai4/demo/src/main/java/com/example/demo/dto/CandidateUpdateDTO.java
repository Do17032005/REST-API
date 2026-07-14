package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateUpdateDTO {

    @NotBlank(message = "Address must not be blank")
    private String address;

    @Size(max = 200, message = "Bio must be at most 200 characters")
    private String bio;
}
