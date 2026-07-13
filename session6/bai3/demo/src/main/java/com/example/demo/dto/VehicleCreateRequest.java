package com.example.demo.dto;

import com.example.demo.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCreateRequest {
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;
}