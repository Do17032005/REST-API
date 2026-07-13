package com.example.demo.repositories;

import com.example.demo.dto.TicketSummaryResponse;
import com.example.demo.model.ParkingTicket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    Optional<ParkingTicket> findTopByVehicle_IdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long vehicleId);

    @Query("""
            select new com.example.demo.dto.TicketSummaryResponse(
                pt.id,
                vehicle.licensePlate,
                zone.name,
                pt.checkInTime,
                pt.checkOutTime
            )
            from ParkingTicket pt
            join pt.vehicle vehicle
            join pt.zone zone
            where pt.checkInTime >= :startOfDay
              and pt.checkInTime < :endOfDay
            order by pt.checkInTime desc
            """)
    List<TicketSummaryResponse> findTodayTicketSummaries(@Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);
}