package com.example.demo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketSummaryResponse {
    private Long id;
    private String licensePlate;
    private String zoneName;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
}