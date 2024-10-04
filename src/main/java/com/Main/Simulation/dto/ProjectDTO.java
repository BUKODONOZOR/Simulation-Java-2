package com.Main.Simulation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class ProjectDTO {
    private String name;
    private String description;
    private List<TaskDTO> tasks;
}
