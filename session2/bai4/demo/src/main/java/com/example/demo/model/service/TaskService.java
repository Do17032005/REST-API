package com.example.demo.model.service;

import com.example.demo.model.entity.Task;
import com.example.demo.model.entity.User;
import com.example.demo.model.repositories.TaskRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public boolean createTask(Task newTask) {
        if (newTask == null || newTask.getAssignedTo() == null || newTask.getAssignedTo().getId() == null) {
            return false;
        }

        int assignedUserId = newTask.getAssignedTo().getId().intValue();
        User assignedUser = userService.findUserById(assignedUserId);
        if (assignedUser == null) {
            return false;
        }

        newTask.setAssignedTo(assignedUser);
        taskRepository.save(newTask);
        return true;
    }
}