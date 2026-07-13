package com.example.demo.service;

import com.example.demo.dto.TicketRequest;
import com.example.demo.dto.TicketResponse;

public interface ParkingService {
    TicketResponse checkIn(TicketRequest request);

    TicketResponse checkOut(Long vehicleId);
}