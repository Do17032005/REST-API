package com.example.demo.controller;

import com.example.demo.model.StudentEnrollment;
import com.example.demo.service.EnrollmentService;
import com.example.demo.response.ApiResponse;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentEnrollment>>> findAllEnrollments() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Retrieved enrollments successfully",
                    enrollmentService.findAllEnrollments()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> findEnrollmentById(@PathVariable Long id) {
        try {
            StudentEnrollment enrollment = enrollmentService.getEnrollmentById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Retrieved enrollment successfully", enrollment));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentEnrollment>> createEnrollment(@RequestBody StudentEnrollment enrollment) {
        try {
            StudentEnrollment created = enrollmentService.createEnrollment(enrollment);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Created enrollment successfully", created));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> updateEnrollment(@PathVariable Long id,
            @RequestBody StudentEnrollment enrollment) {
        try {
            StudentEnrollment updated = enrollmentService.updateEnrollment(id, enrollment);
            return ResponseEntity.ok(new ApiResponse<>(true, "Updated enrollment successfully", updated));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> deleteEnrollment(@PathVariable Long id) {
        try {
            StudentEnrollment deleted = enrollmentService.deleteEnrollmentById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Deleted enrollment successfully", deleted));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }
}