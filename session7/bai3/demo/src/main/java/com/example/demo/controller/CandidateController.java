package com.example.demo.controller;

import com.example.demo.dto.CandidateCreateDTO;
import com.example.demo.entity.Candidate;
import com.example.demo.repository.CandidateRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.CandidateUpdateDTO;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateRepository candidateRepository;

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@Valid @RequestBody CandidateCreateDTO dto) {
        Candidate candidate = Candidate.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .yearsOfExperience(dto.getYearsOfExperience())
                .build();

        Candidate savedCandidate = candidateRepository.save(candidate);
        
        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate> updateCandidate(
            @PathVariable Integer id,
            @Valid @ModelAttribute CandidateUpdateDTO dto) {
        
        return candidateRepository.findById(id).map(candidate -> {
            candidate.setAddress(dto.getAddress());
            candidate.setBio(dto.getBio());
            Candidate updatedCandidate = candidateRepository.save(candidate);
            return ResponseEntity.ok(updatedCandidate);
        }).orElse(ResponseEntity.notFound().build());
    }
}
