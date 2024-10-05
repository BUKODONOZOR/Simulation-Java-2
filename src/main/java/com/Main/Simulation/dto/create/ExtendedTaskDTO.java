package com.Main.Simulation.dto.create;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ExtendedTaskDTO {

    private String title;
    private String description;
    private LocalDate dueDate;
    private Long assignedUserId; // ID del usuario asignado
    private String status; // Estado de la tarea (opcional)
    private String priority; // Prioridad de la tarea (opcional)


}
