package com.Main.Simulation.controller;

import com.Main.Simulation.dto.TaskDTO;
import com.Main.Simulation.entity.Task;
import com.Main.Simulation.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Endpoint to create a task (must be associated with a project and user)
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);

        // Convert entity to DTO for the response
        TaskDTO responseTaskDTO = mapToDTO(createdTask);
        return ResponseEntity.ok(responseTaskDTO);
    }

    // Endpoint to get all tasks
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        List<TaskDTO> taskDTOs = tasks.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(taskDTOs);
    }

    // Endpoint to get tasks assigned to a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDTO>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        List<TaskDTO> taskDTOs = tasks.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(taskDTOs);
    }

    // Helper method to map Task entity to TaskDTO
    private TaskDTO mapToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setAssignedUserId(task.getAssignedUser().getId());
        return dto;
    }
}
