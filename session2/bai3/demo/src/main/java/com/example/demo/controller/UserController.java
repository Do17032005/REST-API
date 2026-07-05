package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.model.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam String search) {
        List<User> users = userService.findAllUsers().stream()
                .filter(user -> user.getUsername() != null
                        && user.getUsername().toLowerCase().contains(search.toLowerCase()))
                .toList();

        return ResponseEntity.ok(users);
    }
}