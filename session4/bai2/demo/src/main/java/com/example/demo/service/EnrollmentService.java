package com.example.demo.service;

import com.example.demo.model.StudentEnrollment;
import com.example.demo.repositories.EnrollmentRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<StudentEnrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public StudentEnrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found"));
    }

    public StudentEnrollment createEnrollment(StudentEnrollment enrollment) {
        return enrollmentRepository.create(enrollment);
    }

    public StudentEnrollment updateEnrollment(Long id, StudentEnrollment enrollment) {
        return enrollmentRepository.update(id, enrollment);
    }

    public StudentEnrollment deleteEnrollmentById(Long id) {
        return enrollmentRepository.deleteById(id);
    }
}