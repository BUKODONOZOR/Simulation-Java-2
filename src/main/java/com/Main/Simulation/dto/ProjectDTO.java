package com.Main.Simulation.dto;

import java.util.List;

public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private List<TaskDTO> tasks; // To include tasks within the project


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
