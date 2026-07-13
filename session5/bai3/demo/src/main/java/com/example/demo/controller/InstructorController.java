package com.example.demo.controller;

import com.example.demo.dto.InstructorCreateRequest;
import com.example.demo.model.Instructor;
import com.example.demo.service.InstructorService;
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
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> findAllInstructors() {
        try {
            return ResponseEntity.ok(new ApiResponse<>(true, "Retrieved instructors successfully",
                    instructorService.findAllInstructors()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> findInstructorById(@PathVariable Long id) {
        try {
            Instructor instructor = instructorService.getInstructorById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Retrieved instructor successfully", instructor));
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody InstructorCreateRequest req) {
        try {
            Instructor created = instructorService.createInstructor(req);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Created instructor successfully", created));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Long id,
            @RequestBody Instructor instructor) {
        try {
            Instructor updated = instructorService.updateInstructor(id, instructor);
            return ResponseEntity.ok(new ApiResponse<>(true, "Updated instructor successfully", updated));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable Long id) {
        try {
            Instructor deleted = instructorService.deleteInstructorById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Deleted instructor successfully", deleted));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }
}