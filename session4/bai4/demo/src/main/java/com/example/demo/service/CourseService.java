package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repositories.CourseRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long id) throws NoSuchElementException;
    Course saveCourse(Course course);
    Course updateCourse(Long id, Course course) throws NoSuchElementException;
    void deleteCourse(Long id) throws NoSuchElementException;
}

  