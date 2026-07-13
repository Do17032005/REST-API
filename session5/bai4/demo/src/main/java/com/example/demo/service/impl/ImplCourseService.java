package com.example.demo.service.impl;

import com.example.demo.dto.CourseResponseV2;
import com.example.demo.dto.PageResponse;
import com.example.demo.model.Course;
import com.example.demo.model.CourseStatus;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.service.CourseService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ImplCourseService implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public ImplCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Page<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction) {
        int safePage = Math.max(page, 0);
        String sortField = (sortBy == null || sortBy.isBlank()) ? "id" : sortBy;
        PageRequest pageable = PageRequest.of(safePage, size, Sort.by(direction, sortField));

        return courseRepository.findAll(pageable)
                .map(this::toCourseResponse);
    }

    @Override
    public PageResponse<CourseResponseV2> getPagedCoursesByStatus(int page, int size, String sortBy,
            Sort.Direction direction, CourseStatus status) {
        int safePage = Math.max(page, 0);
        String sortField = (sortBy == null || sortBy.isBlank()) ? "id" : sortBy;
        PageRequest pageable = PageRequest.of(safePage, size, Sort.by(direction, sortField));

        Page<CourseResponseV2> courseResponses = courseRepository.findAllByStatus(status, pageable)
                .map(this::toCourseResponse);

        return new PageResponse<>(
                courseResponses.getContent(),
                courseResponses.getNumber(),
                courseResponses.getSize(),
                Math.toIntExact(courseResponses.getTotalElements()),
                courseResponses.getTotalPages(),
                courseResponses.isLast());
    }

    @Override
    public Course getCourseById(Long id) throws NoSuchElementException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) throws NoSuchElementException {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
        existingCourse.setTitle(course.getTitle());
        existingCourse.setStatus(course.getStatus());
        existingCourse.setInstructor(course.getInstructor());
        return courseRepository.save(existingCourse);
    }

    @Override
    public Course deleteCourse(Long id) throws NoSuchElementException {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
        courseRepository.delete(existingCourse);
        return existingCourse;
    }

    private CourseResponseV2 toCourseResponseV2(Course course) {
        return new CourseResponseV2(course.getId(), course.getTitle(), course.getStatus());
    }

}
