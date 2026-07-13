package com.example.demo.service;

import com.example.demo.dto.TicketSummaryResponse;
import com.example.demo.dto.TicketRequest;
import com.example.demo.dto.TicketResponse;
import java.util.List;

public interface ParkingService {
    TicketResponse checkIn(TicketRequest request);

    TicketResponse checkOut(Long vehicleId);

    List<TicketSummaryResponse> getTodayTicketSummaries();
}