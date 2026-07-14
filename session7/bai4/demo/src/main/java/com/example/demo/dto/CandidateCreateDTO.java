package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCreateDTO {

    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 5, max = 50, message = "Họ tên phải từ 5 đến 50 ký tự")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @Min(value = 18, message = "Tuổi tối thiểu là 18")
    private Integer age;

    @Min(value = 0, message = "Năm kinh nghiệm không được là số âm")
    private Integer yearsOfExperience;

    @Pattern(regexp = "^0[35789]\\d{8}$", message = "Số điện thoại không hợp lệ")
    private String phone;
}
