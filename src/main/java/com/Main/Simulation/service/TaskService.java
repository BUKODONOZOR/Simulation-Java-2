package com.Main.Simulation.service;

import com.Main.Simulation.dto.TaskDTO;
import com.Main.Simulation.entity.Project;
import com.Main.Simulation.entity.Task;
import com.Main.Simulation.entity.User;
import com.Main.Simulation.repository.TaskRepository;
import com.Main.Simulation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByAssignedUser_Id(userId);
    }

    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        // Manejo de la fecha de vencimiento
        try {
            task.setDueDate(taskDTO.getDueDate());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha no válido: " + taskDTO.getDueDate(), e);
        }

        // Establecer referencia al proyecto
        Project project = new Project();
        task.setProject(project);

        // Establecer referencia al usuario asignado
        User user = userRepository.findById(taskDTO.getAssignedUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        task.setAssignedUser(user);

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
