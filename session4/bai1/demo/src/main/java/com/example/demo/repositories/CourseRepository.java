package com.example.demo.repositories;

import com.example.demo.model.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.CourseStatus;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>(List.of(
            new Course(1L, "Spring Boot Basics", CourseStatus.ACTIVE, 1L),
            new Course(2L, "REST API Design", CourseStatus.ACTIVE, 2L)));
    private long nextId = 3L;

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(long id) {
        return courses.stream()
                .filter(course -> course.getId() != null && course.getId().equals(id))
                .findFirst();
    }

    public Course create(Course course) {
        Course created = new Course(nextId++, course.getTitle(), course.getStatus(), course.getInstructorId());
        courses.add(created);
        return created;
    }

    public Course update(Long id, Course course) {
        Course existing = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        Course updated = new Course(existing.getId(), course.getTitle(), course.getStatus(), course.getInstructorId());
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