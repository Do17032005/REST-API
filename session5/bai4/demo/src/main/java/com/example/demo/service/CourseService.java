package com.example.demo.service;

import com.example.demo.dto.CourseResponseV2;
import com.example.demo.dto.PageResponse;
import com.example.demo.model.Course;
import com.example.demo.model.CourseStatus;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface CourseService {
    List<Course> getAllCourses();

    Course getCourseById(Long id) throws NoSuchElementException;

    Course saveCourse(Course course);

    Course updateCourse(Long id, Course course) throws NoSuchElementException;

    Course deleteCourse(Long id) throws NoSuchElementException;

    Page<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction);

    PageResponse<CourseResponseV2> getPagedCoursesByStatus(int page, int size, String sortBy,
            Sort.Direction direction, CourseStatus status);
}
