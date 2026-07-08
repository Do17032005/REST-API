package com.example.demo.service;

import com.example.demo.dto.InstructorCreateRequest;
import com.example.demo.model.Instructor;
import com.example.demo.repositories.InstructorRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Instructor not found"));
    }

    public Instructor createInstructor(InstructorCreateRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(Long id, Instructor instructor) {
        Instructor existing = instructorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Instructor not found"));
        existing.setName(instructor.getName());
        existing.setEmail(instructor.getEmail());
        existing.setCourses(instructor.getCourses());
        return instructorRepository.save(existing);
    }

    public Instructor deleteInstructorById(Long id) {
        Instructor existing = instructorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Instructor not found"));
        instructorRepository.delete(existing);
        return existing;
    }
}