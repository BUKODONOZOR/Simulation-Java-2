package com.Main.Simulation.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter

public class BasicTaskDTO {

    private String title;
    private String description;
    private LocalDate dueDate;
    private Long assignedUserId; // ID del usuario asignado


}
