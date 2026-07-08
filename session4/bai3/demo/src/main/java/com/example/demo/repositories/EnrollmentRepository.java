package com.example.demo.repositories;

import com.example.demo.model.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {

}