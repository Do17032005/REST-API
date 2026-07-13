package com.example.demo.service;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.VehicleCreateRequest;
import com.example.demo.dto.VehicleResponse;

public interface VehicleService {
    VehicleResponse createVehicle(VehicleCreateRequest request);

    PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction,
            String keyword);
}