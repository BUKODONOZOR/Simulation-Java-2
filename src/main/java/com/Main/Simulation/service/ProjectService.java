package com.Main.Simulation.service;

import com.Main.Simulation.dto.ProjectDTO;
import com.Main.Simulation.dto.TaskDTO;
import com.Main.Simulation.entity.Project;
import com.Main.Simulation.entity.Task;
import com.Main.Simulation.repository.ProjectRepository;
import com.Main.Simulation.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Project createProjectWithTasks(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());

        Project savedProject = projectRepository.save(project);

        // Handle tasks if they exist
        if (projectDTO.getTasks() != null) {
            for (TaskDTO taskDTO : projectDTO.getTasks()) {
                Task task = new Task();
                task.setTitle(taskDTO.getTitle());
                task.setDescription(taskDTO.getDescription());
                task.setDueDate(LocalDateTime.parse(taskDTO.getDueDate())); // Parse date
                task.setProject(savedProject);
                taskRepository.save(task);
            }
        }

        return savedProject;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }
}
