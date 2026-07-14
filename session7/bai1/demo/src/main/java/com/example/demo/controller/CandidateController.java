package com.example.demo.controller;

import com.example.demo.dto.CandidateCreateDTO;
import com.example.demo.entity.Candidate;
import com.example.demo.repository.CandidateRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
