package com.Main.Simulation.controller;

import com.Main.Simulation.dto.create.ProjectCreateDTO;
import com.Main.Simulation.dto.response.BasicTaskDTO;
import com.Main.Simulation.entity.Project;
import com.Main.Simulation.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateDTO projectCreateDTO) {
        Project createdProject = projectService.createProjectWithTasks(projectCreateDTO);
        return ResponseEntity.ok(createdProject);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<BasicTaskDTO>> getTasksForProject(@PathVariable Long id) {
        List<BasicTaskDTO> tasks = projectService.getTasksForProject(id);
        return ResponseEntity.ok(tasks);
    }
}
