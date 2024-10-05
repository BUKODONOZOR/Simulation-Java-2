package com.Main.Simulation.dto.create;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProjectCreateDTO {
    private String name;
    private String description;
    private List<ExtendedTaskDTO> tasks;
}