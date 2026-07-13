package com.example.demo.service.impl;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.VehicleCreateRequest;
import com.example.demo.dto.VehicleResponse;
import com.example.demo.model.Vehicle;
import com.example.demo.repositories.VehicleRepository;
import com.example.demo.service.VehicleService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ImplVehicleService implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public ImplVehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleResponse createVehicle(VehicleCreateRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(request.getLicensePlate());
        vehicle.setColor(request.getColor());
        vehicle.setType(request.getVehicleType());
        vehicle.setParkingTickets(new ArrayList<>());
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return new VehicleResponse(savedVehicle.getId(), savedVehicle.getLicensePlate(), savedVehicle.getColor(),
                savedVehicle.getType());
    }

    @Override
    public PageResponse<VehicleResponse> getPagedVehicles(int page, int size, String sortBy, String direction,
            String keyword) {
        int safePage = Math.max(page, 0);
        Pageable pageable = buildPageable(safePage, size, sortBy, direction);

        String normalizedKeyword = (keyword == null || keyword.isBlank()) ? null : keyword.trim();
        Page<VehicleResponse> vehicleResponses = vehicleRepository.findAllByKeyword(normalizedKeyword, pageable);

        return new PageResponse<>(
                vehicleResponses.getContent(),
                vehicleResponses.getNumber(),
                vehicleResponses.getSize(),
                vehicleResponses.getTotalElements(),
                vehicleResponses.getTotalPages(),
                vehicleResponses.isLast());
    }

    private Pageable buildPageable(int page, int size, String sortBy, String direction) {
        if (sortBy == null || sortBy.isBlank() || direction == null || direction.isBlank()) {
            return PageRequest.of(page, size, Sort.unsorted());
        }

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        return PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
    }
}