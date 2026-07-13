package com.example.demo.repositories;

import com.example.demo.model.ParkingTicket;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    Optional<ParkingTicket> findTopByVehicle_IdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);
}