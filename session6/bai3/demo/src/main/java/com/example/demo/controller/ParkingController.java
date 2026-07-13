package com.example.demo.controller;

import com.example.demo.dto.TicketRequest;
import com.example.demo.dto.TicketResponse;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tickets")
public class ParkingController {

    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<TicketResponse>> checkIn(@RequestBody TicketRequest request) {
        TicketResponse response = parkingService.checkIn(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Check-in successfully", response));
    }

    @PutMapping("/check-out/{vehicleId}")
    public ResponseEntity<ApiResponse<TicketResponse>> checkOut(@PathVariable Long vehicleId) {
        TicketResponse response = parkingService.checkOut(vehicleId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Check-out successfully", response));
    }
}