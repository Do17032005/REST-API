package com.example.demo.controller;

import com.example.demo.dto.CourseResponseV2;
import com.example.demo.dto.PageResponse;
import com.example.demo.model.Course;
import com.example.demo.model.CourseStatus;
import com.example.demo.service.CourseService;
import com.example.demo.response.ApiResponse;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> findAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(defaultValue = "ACTIVE") CourseStatus status) {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Retrieved courses successfully",
                    courseService.getPagedCoursesByStatus(page, size, sortBy, direction, status)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> findCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Retrieved course successfully", course));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody Course course) {
        try {
            Course created = courseService.saveCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Created course successfully", created));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        try {
            Course updated = courseService.updateCourse(id, course);
            return ResponseEntity.ok(new ApiResponse<>(true, "Updated course successfully", updated));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourse(@PathVariable Long id) {
        try {
            Course deleted = courseService.deleteCourse(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Deleted course successfully", deleted));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }
}