package com.Main.Simulation.dto;

public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String dueDate; // Use String to simplify date handling
    private Long projectId;
    private Long assignedUserId;


    public Long getId() {
        return id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
}
