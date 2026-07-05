package com.example.demo.controller;

import com.example.demo.model.entity.Task;
import com.example.demo.model.service.TaskService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam String search) {
        List<Task> tasks = taskService.findAllTasks().stream()
                .filter(task -> task.getTitle() != null
                        && task.getTitle().toLowerCase().contains(search.toLowerCase()))
                .toList();

        return ResponseEntity.ok(tasks);
    }
}