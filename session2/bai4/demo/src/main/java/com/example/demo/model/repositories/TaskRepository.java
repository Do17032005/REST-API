package com.example.demo.model.repositories;

import com.example.demo.model.entity.Task;
import com.example.demo.model.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>(List.of(
            new Task(1L, "Design API", "Design the REST endpoints", "HIGH",
                    new User(1L, "alice", "alice@example.com", "ADMIN")),
            new Task(2L, "Create DB Schema", "Prepare the database schema", "HIGH",
                    new User(3L, "charlie", "charlie@example.com", "MANAGER")),
            new Task(3L, "Implement Auth", "Add authentication layer", "HIGH",
                    new User(2L, "bob", "bob@example.com", "USER")),
            new Task(4L, "Write Tests", "Add unit tests for services", "MEDIUM",
                    new User(2L, "bob", "bob@example.com", "USER")),
            new Task(5L, "Setup Swagger", "Document APIs with Swagger", "LOW",
                    new User(1L, "alice", "alice@example.com", "ADMIN")),
            new Task(6L, "Fix Validation", "Improve request validation", "MEDIUM",
                    new User(3L, "charlie", "charlie@example.com", "MANAGER")),
            new Task(7L, "Optimize Queries", "Reduce database query count", "HIGH",
                    new User(1L, "alice", "alice@example.com", "ADMIN")),
            new Task(8L, "Add Logging", "Standardize application logs", "LOW",
                    new User(2L, "bob", "bob@example.com", "USER")),
            new Task(9L, "Review Code", "Review recent pull requests", "MEDIUM",
                    new User(3L, "charlie", "charlie@example.com", "MANAGER")),
            new Task(10L, "Deploy App", "Deploy the application to staging", "HIGH",
                                        new User(1L, "alice", "alice@example.com", "ADMIN")));

        public List<Task> findAll() {
                return tasks;
        }

        public void save(Task task) {
                tasks.add(task);
        }
}