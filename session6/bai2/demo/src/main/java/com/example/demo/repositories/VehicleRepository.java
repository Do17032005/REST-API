package com.example.demo.repositories;

import com.example.demo.dto.VehicleResponse;
import com.example.demo.model.Vehicle;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByLicensePlate(String licensePlate);

    @Query("""
            select new com.example.demo.dto.VehicleResponse(v.id, v.licensePlate, v.color, v.type)
            from Vehicle v
            where :keyword is null
               or lower(v.licensePlate) like lower(concat('%', :keyword, '%'))
               or lower(v.color) like lower(concat('%', :keyword, '%'))
            """)
    Page<VehicleResponse> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);
}