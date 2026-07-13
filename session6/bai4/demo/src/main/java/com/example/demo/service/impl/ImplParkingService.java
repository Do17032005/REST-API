package com.example.demo.service.impl;

import com.example.demo.dto.TicketSummaryResponse;
import com.example.demo.dto.TicketRequest;
import com.example.demo.dto.TicketResponse;
import com.example.demo.model.ParkingTicket;
import com.example.demo.model.Vehicle;
import com.example.demo.model.Zone;
import com.example.demo.repositories.ParkingTicketRepository;
import com.example.demo.repositories.VehicleRepository;
import com.example.demo.repositories.ZoneRepository;
import com.example.demo.service.ParkingService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImplParkingService implements ParkingService {

    private final VehicleRepository vehicleRepository;
    private final ZoneRepository zoneRepository;
    private final ParkingTicketRepository parkingTicketRepository;

    @Autowired
    public ImplParkingService(VehicleRepository vehicleRepository, ZoneRepository zoneRepository,
            ParkingTicketRepository parkingTicketRepository) {
        this.vehicleRepository = vehicleRepository;
        this.zoneRepository = zoneRepository;
        this.parkingTicketRepository = parkingTicketRepository;
    }

    @Override
    @Transactional
    public TicketResponse checkIn(TicketRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));
        Zone zone = zoneRepository.findById(request.getZoneId())
                .orElseThrow(() -> new IllegalArgumentException("Zone not found"));

        if (zone.getOccupiedSpots() == null) {
            zone.setOccupiedSpots(0);
        }

        if (zone.getOccupiedSpots() >= zone.getCapacity()) {
            throw new IllegalStateException("Zone is full");
        }

        ParkingTicket ticket = new ParkingTicket();
        ticket.setVehicle(vehicle);
        ticket.setZone(zone);
        ticket.setCheckInTime(LocalDateTime.now());
        ticket.setCheckOutTime(null);

        zone.setOccupiedSpots(zone.getOccupiedSpots() + 1);
        ParkingTicket savedTicket = parkingTicketRepository.save(ticket);

        return toResponse(savedTicket);
    }

    @Override
    @Transactional
    public TicketResponse checkOut(Long vehicleId) {
        ParkingTicket ticket = parkingTicketRepository
                .findTopByVehicleIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Active parking ticket not found"));

        Zone zone = ticket.getZone();
        if (zone.getOccupiedSpots() == null || zone.getOccupiedSpots() <= 0) {
            zone.setOccupiedSpots(0);
        } else {
            zone.setOccupiedSpots(zone.getOccupiedSpots() - 1);
        }

        ticket.setCheckOutTime(LocalDateTime.now());
        ParkingTicket savedTicket = parkingTicketRepository.save(ticket);

        return toResponse(savedTicket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketSummaryResponse> getTodayTicketSummaries() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        return parkingTicketRepository.findTodayTicketSummaries(startOfDay, endOfDay);
    }

    private TicketResponse toResponse(ParkingTicket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getVehicle().getLicensePlate(),
                ticket.getZone().getName(),
                ticket.getCheckInTime(),
                ticket.getCheckOutTime());
    }
}