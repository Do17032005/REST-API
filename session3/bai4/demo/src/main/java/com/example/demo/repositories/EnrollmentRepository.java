package com.example.demo.repositories;

import com.example.demo.model.Enrollment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>(List.of(
            new Enrollment(1L, "Nguyen Van A", 1L),
            new Enrollment(2L, "Tran Thi B", 2L)));
    private long nextId = 3L;

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Optional<Enrollment> findById(long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId() != null && enrollment.getId().equals(id))
                .findFirst();
    }

    public Enrollment create(Enrollment enrollment) {
        Enrollment created = new Enrollment(nextId++, enrollment.getStudentName(), enrollment.getCourseId());
        enrollments.add(created);
        return created;
    }

    public Enrollment update(Long id, Enrollment enrollment) {
        Enrollment existing = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        Enrollment updated = new Enrollment(existing.getId(), enrollment.getStudentName(), enrollment.getCourseId());
        for (int index = 0; index < enrollments.size(); index++) {
            if (enrollments.get(index).getId().equals(id)) {
                enrollments.set(index, updated);
                break;
            }
        }
        return updated;
    }

    public Enrollment deleteById(Long id) {
        Enrollment existing = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        enrollments.remove(existing);
        return existing;
    }
}