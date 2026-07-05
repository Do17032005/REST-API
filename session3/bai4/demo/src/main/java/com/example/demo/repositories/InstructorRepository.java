package com.example.demo.repositories;

import com.example.demo.model.Instructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>(List.of(
            new Instructor(1L, "Alice Johnson", "alice@example.com"),
            new Instructor(2L, "Bob Smith", "bob@example.com")));
    private long nextId = 3L;

    public List<Instructor> findAll() {
        return instructors;
    }

    public Optional<Instructor> findById(long id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId() != null && instructor.getId().equals(id))
                .findFirst();
    }

    public Instructor create(Instructor instructor) {
        Instructor created = new Instructor(nextId++, instructor.getName(), instructor.getEmail());
        instructors.add(created);
        return created;
    }

    public Instructor update(Long id, Instructor instructor) {
        Instructor existing = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
        Instructor updated = new Instructor(existing.getId(), instructor.getName(), instructor.getEmail());
        for (int index = 0; index < instructors.size(); index++) {
            if (instructors.get(index).getId().equals(id)) {
                instructors.set(index, updated);
                break;
            }
        }
        return updated;
    }

    public Instructor deleteById(Long id) {
        Instructor existing = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found"));
        instructors.remove(existing);
        return existing;
    }
}