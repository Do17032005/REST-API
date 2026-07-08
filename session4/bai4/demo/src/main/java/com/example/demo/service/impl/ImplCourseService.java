package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.service.CourseService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteCourse(Long id) throws NoSuchElementException {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));
        courseRepository.delete(existingCourse);
    }

}
