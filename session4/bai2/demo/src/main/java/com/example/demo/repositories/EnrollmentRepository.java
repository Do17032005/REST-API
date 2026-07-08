package com.example.demo.repositories;

import com.example.demo.model.Course;
import com.example.demo.model.CourseStatus;
import com.example.demo.model.Student;
import com.example.demo.model.StudentEnrollment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentRepository {

    private final List<StudentEnrollment> enrollments = new ArrayList<>(List.of(
            new StudentEnrollment(1L, createCourse(1L, "Spring Boot Basics", CourseStatus.ACTIVE),
                    createStudent(1L, "Nguyen Van A", "a@example.com")),
            new StudentEnrollment(2L, createCourse(2L, "REST API Design", CourseStatus.ACTIVE),
                    createStudent(2L, "Tran Thi B", "b@example.com"))));
    private long nextId = 3L;

    private static Student createStudent(Long id, String name, String email) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        return student;
    }

    private static Course createCourse(Long id, String title, CourseStatus status) {
        Course course = new Course();
        course.setId(id);
        course.setTitle(title);
        course.setStatus(status);
        return course;
    }

    public List<StudentEnrollment> findAll() {
        return enrollments;
    }

    public Optional<StudentEnrollment> findById(long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId() != null && enrollment.getId().equals(id))
                .findFirst();
    }

    public StudentEnrollment create(StudentEnrollment enrollment) {
        StudentEnrollment created = new StudentEnrollment(nextId++, enrollment.getCourse(), enrollment.getStudent());
        enrollments.add(created);
        return created;
    }

    public StudentEnrollment update(Long id, StudentEnrollment enrollment) {
        StudentEnrollment existing = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        StudentEnrollment updated = new StudentEnrollment(existing.getId(), enrollment.getCourse(),
                enrollment.getStudent());
        for (int index = 0; index < enrollments.size(); index++) {
            if (enrollments.get(index).getId().equals(id)) {
                enrollments.set(index, updated);
                break;
            }
        }
        return updated;
    }

    public StudentEnrollment deleteById(Long id) {
        StudentEnrollment existing = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
        enrollments.remove(existing);
        return existing;
    }
}