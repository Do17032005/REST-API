package com.example.demo.repositories;

import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
}