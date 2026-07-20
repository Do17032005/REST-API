package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Department>> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        Department createdDepartment = departmentService.createDepartment(departmentDTO);
        ApiResponse<Department> response = new ApiResponse<>("SUCCESS", "Department created successfully", createdDepartment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
