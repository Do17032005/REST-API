package com.example.demo.service;

import com.example.demo.dto.CourseResponse;
import com.example.demo.dto.PageResponse;
import com.example.demo.model.Course;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Sort;

public interface CourseService {
    List<Course> getAllCourses();

    Course getCourseById(Long id) throws NoSuchElementException;

    Course saveCourse(Course course);

    Course updateCourse(Long id, Course course) throws NoSuchElementException;

    Course deleteCourse(Long id) throws NoSuchElementException;

    PageResponse<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction);
}
