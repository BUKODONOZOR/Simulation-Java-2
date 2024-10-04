package com.Main.Simulation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;



@Entity
@Data
public class Task extends Audit {

    @NotNull
    private String title;

    private String description;

    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private User assignedUser;

}
