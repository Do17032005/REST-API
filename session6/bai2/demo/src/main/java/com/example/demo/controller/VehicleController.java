package com.example.demo.controller;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.VehicleCreateRequest;
import com.example.demo.dto.VehicleResponse;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> createVehicle(@RequestBody VehicleCreateRequest request) {
        VehicleResponse created = vehicleService.createVehicle(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Created vehicle successfully", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<VehicleResponse>>> getPagedVehicles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) String keyword) {
        PageResponse<VehicleResponse> result = vehicleService.getPagedVehicles(page, size, sortBy, direction, keyword);
        return ResponseEntity.ok(new ApiResponse<>(true, "Retrieved vehicles successfully", result));
    }
}