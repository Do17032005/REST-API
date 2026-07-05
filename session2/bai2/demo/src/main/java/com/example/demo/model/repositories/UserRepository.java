package com.example.demo.model.repositories;

import com.example.demo.model.entity.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final List<User> users = List.of(
            new User(1L, "alice", "alice@example.com", "ADMIN"),
            new User(2L, "bob", "bob@example.com", "USER"),
            new User(3L, "charlie", "charlie@example.com", "MANAGER"));

    public List<User> findAll() {
        return users;
    }
}