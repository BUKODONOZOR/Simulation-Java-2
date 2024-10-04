package com.Main.Simulation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TaskDTO {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Long assignedUserId;
}
