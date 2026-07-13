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
        return enrollmentRepository.save(enrollment);
    }

    public StudentEnrollment updateEnrollment(Long id, StudentEnrollment enrollment) {
        StudentEnrollment existing = enrollmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found"));
        existing.setCourse(enrollment.getCourse());
        existing.setStudent(enrollment.getStudent());
        return enrollmentRepository.save(existing);
    }

    public StudentEnrollment deleteEnrollmentById(Long id) {
        StudentEnrollment existing = enrollmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Enrollment not found"));
        enrollmentRepository.delete(existing);
        return existing;
    }
}