package com.Main.Simulation.service;

import com.Main.Simulation.dto.create.ExtendedTaskDTO;
import com.Main.Simulation.dto.create.ProjectCreateDTO;
import com.Main.Simulation.dto.response.BasicTaskDTO;
import com.Main.Simulation.entity.Project;
import com.Main.Simulation.entity.Task;
import com.Main.Simulation.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskService taskService;

    @Transactional
    public Project createProjectWithTasks(ProjectCreateDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());

        Project savedProject = projectRepository.save(project);

        if (projectDTO.getTasks() != null && !projectDTO.getTasks().isEmpty()) {
            for (ExtendedTaskDTO extendedTaskDTO : projectDTO.getTasks()) {
                taskService.createTaskFromExtendedDTO(extendedTaskDTO, savedProject.getId());
            }
        }

        return savedProject;
    }

    /**
     * Obtener todos los proyectos.
     */
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    /**
     * Obtener un proyecto por su ID.
     */
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    /**
     * Obtener las tareas de un proyecto específico en formato DTO.
     */
    public List<BasicTaskDTO> getTasksForProject(Long projectId) {
        Project project = getProjectById(projectId);
        if (project != null && project.getTasks() != null) {
            return taskService.convertTasksToBasicDTOs(project.getTasks());
        }
        return List.of();  // Retorna una lista vacía si no hay tareas o el proyecto no existe
    }
}
