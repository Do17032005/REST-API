package com.example.demo.repositories;

import com.example.demo.model.Course;
import com.example.demo.model.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.status = :status")
    Page<Course> findAllByStatus(@Param("status") CourseStatus status, Pageable pageable);

}
