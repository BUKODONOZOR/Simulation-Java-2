package com.Main.Simulation.controller;

import com.Main.Simulation.dto.create.ExtendedTaskDTO;
import com.Main.Simulation.dto.response.BasicTaskDTO;
import com.Main.Simulation.entity.Task;
import com.Main.Simulation.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping("/create")
    public ResponseEntity<BasicTaskDTO> createTask(@RequestBody ExtendedTaskDTO taskDTO,
                                                   @RequestParam(required = false) Long projectId) {
        Task createdTask = taskService.createTask(taskDTO, projectId);
        BasicTaskDTO basicTaskDTO = taskService.mapToBasicDTO(createdTask);
        return ResponseEntity.ok(basicTaskDTO);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> taskDTOs = taskService.getAllTasks();
        return ResponseEntity.ok(taskDTOs);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BasicTaskDTO>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        List<BasicTaskDTO> taskDTOs = taskService.convertTasksToBasicDTOs(tasks);
        return ResponseEntity.ok(taskDTOs);
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task taskDTO = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDTO);
    }
}
