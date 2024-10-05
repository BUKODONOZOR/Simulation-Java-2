package com.Main.Simulation.service;

import com.Main.Simulation.dto.create.ExtendedTaskDTO;
import com.Main.Simulation.dto.response.BasicTaskDTO;
import com.Main.Simulation.entity.Project;
import com.Main.Simulation.entity.Task;
import com.Main.Simulation.entity.User;
import com.Main.Simulation.repository.ProjectRepository;
import com.Main.Simulation.repository.TaskRepository;
import com.Main.Simulation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Crear una tarea a partir de ExtendedTaskDTO y asignarla a un proyecto.
     */
    public Task createTaskFromExtendedDTO(ExtendedTaskDTO extendedTaskDTO, Long projectId) {
        Task task = new Task();
        task.setTitle(extendedTaskDTO.getTitle());
        task.setDescription(extendedTaskDTO.getDescription());

        // Manejo de la fecha
        try {
            task.setDueDate(extendedTaskDTO.getDueDate().atStartOfDay());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha no válido: " + extendedTaskDTO.getDueDate(), e);
        }

        // Asignar el proyecto si el ID es válido
        if (projectId != null) {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
            task.setProject(project);
        }

        // Asignar el usuario si se proporciona el ID del usuario asignado
        if (extendedTaskDTO.getAssignedUserId() != null) {
            User user = userRepository.findById(extendedTaskDTO.getAssignedUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            task.setAssignedUser(user);
        }

        // Guardar la tarea en la base de datos
        return taskRepository.save(task);
    }

    /**
     * Crear una tarea sin asignarla a un proyecto.
     */
    public Task createTask(ExtendedTaskDTO extendedTaskDTO, Long projectId) {
        Task task = new Task();
        task.setTitle(extendedTaskDTO.getTitle());
        task.setDescription(extendedTaskDTO.getDescription());

        // Manejo de la fecha
        try {
            task.setDueDate(extendedTaskDTO.getDueDate().atStartOfDay());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha no válido: " + extendedTaskDTO.getDueDate(), e);
        }

        // Asignar el usuario si se proporciona el ID del usuario asignado
        if (extendedTaskDTO.getAssignedUserId() != null) {
            User user = userRepository.findById(extendedTaskDTO.getAssignedUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            task.setAssignedUser(user);
        }

        // Guardar la tarea en la base de datos
        return taskRepository.save(task);
    }

    /**
     * Obtener todas las tareas.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Obtener tareas por ID de usuario.
     */
    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByAssignedUser_Id(userId);
    }

    /**
     * Convertir una lista de tareas en una lista de BasicTaskDTOs.
     */
    public List<BasicTaskDTO> convertTasksToBasicDTOs(List<Task> tasks) {
        return tasks.stream()
                .map(this::mapToBasicDTO)  // Usa el método del servicio de tareas para convertir a BasicTaskDTO
                .collect(Collectors.toList());
    }

    /**
     * Obtener una tarea por su ID.
     */
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
    }

    /**
     * Convertir una tarea a un BasicTaskDTO.
     */
    public BasicTaskDTO mapToBasicDTO(Task task) {
        BasicTaskDTO dto = new BasicTaskDTO();
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDueDate(LocalDate.from(task.getDueDate()));
        return dto;
    }
}
