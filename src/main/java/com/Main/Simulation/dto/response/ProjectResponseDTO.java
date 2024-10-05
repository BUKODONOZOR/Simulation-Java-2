package com.Main.Simulation.dto.response;

import com.Main.Simulation.dto.create.ExtendedTaskDTO;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProjectResponseDTO {
    private String name;
    private String description;
    private List<ExtendedTaskDTO> tasks;
}
