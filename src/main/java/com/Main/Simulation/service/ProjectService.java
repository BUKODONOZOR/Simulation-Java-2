package com.Main.Simulation.service;

import com.Main.Simulation.dto.ProjectDTO;
import com.Main.Simulation.dto.TaskDTO;
import com.Main.Simulation.entity.Project;
import com.Main.Simulation.entity.Task;
import com.Main.Simulation.entity.User;
import com.Main.Simulation.repository.ProjectRepository;
import com.Main.Simulation.repository.TaskRepository;
import com.Main.Simulation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private UserRepository userRepository;

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
                task.setDueDate(taskDTO.getDueDate());

                if (taskDTO.getDueDate() == null) {
                    throw new IllegalArgumentException("La fecha de vencimiento no puede ser nula");
                }
                if (taskDTO.getAssignedUserId() != null) {
                    Optional<User> userOptional = userRepository.findById(taskDTO.getAssignedUserId());
                    if (userOptional.isPresent()) {
                        task.setAssignedUser(userOptional.get());
                    } else {
                        throw new IllegalArgumentException("El usuario con ID " + taskDTO.getAssignedUserId() + " no existe");
                    }
                }
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
