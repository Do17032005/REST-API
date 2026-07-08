package com.example.demo.repositories;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.CourseStatus;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>(List.of(
            new Course(1L, "Spring Boot Basics", CourseStatus.ACTIVE,
                    createInstructor(1L, "Nguyen Van A", "a@example.com")),
            new Course(2L, "REST API Design", CourseStatus.ACTIVE,
                    createInstructor(2L, "Tran Thi B", "b@example.com"))));
    private long nextId = 3L;

    private static Instructor createInstructor(Long id, String name, String email) {
        Instructor instructor = new Instructor();
        instructor.setId(id);
        instructor.setName(name);
        instructor.setEmail(email);
        return instructor;
    }

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(long id) {
        return courses.stream()
                .filter(course -> course.getId() != null && course.getId().equals(id))
                .findFirst();
    }

    public Course create(Course course) {
        Course created = new Course(nextId++, course.getTitle(), course.getStatus(), course.getInstructor());
        courses.add(created);
        return created;
    }

    public Course update(Long id, Course course) {
        Course existing = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        Course updated = new Course(existing.getId(), course.getTitle(), course.getStatus(), course.getInstructor());
        for (int index = 0; index < courses.size(); index++) {
            if (courses.get(index).getId().equals(id)) {
                courses.set(index, updated);
                break;
            }
        }
        return updated;
    }

    public Course deleteById(Long id) {
        Course existing = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        courses.remove(existing);
        return existing;
    }
}