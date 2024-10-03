package com.Main.Simulation.controller;

import com.Main.Simulation.dto.ProjectDTO;
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

    // Endpoint to create a project along with its tasks
    @PostMapping
    public ResponseEntity<ProjectDTO> createProjectWithTasks(@RequestBody ProjectDTO projectDTO) {
        Project createdProject = projectService.createProjectWithTasks(projectDTO);

        // Convert to DTO to return the response
        ProjectDTO responseProjectDTO = new ProjectDTO();
        responseProjectDTO.setId(createdProject.getId());
        responseProjectDTO.setName(createdProject.getName());
        responseProjectDTO.setDescription(createdProject.getDescription());
        // Populate tasks if necessary (add logic if needed)

        return ResponseEntity.ok(responseProjectDTO);
    }

    // Endpoint to get all projects
    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        // Map entities to DTOs
        List<ProjectDTO> projectDTOs = projects.stream().map(project -> {
            ProjectDTO dto = new ProjectDTO();
            dto.setId(project.getId());
            dto.setName(project.getName());
            dto.setDescription(project.getDescription());
            return dto;
        }).toList();

        return ResponseEntity.ok(projectDTOs);
    }

    // Endpoint to get a project by id
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());

        return ResponseEntity.ok(projectDTO);
    }
}
