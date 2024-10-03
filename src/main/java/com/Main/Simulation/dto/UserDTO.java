package com.Main.Simulation.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserDTO {

    private Long id;
    private String username;
    // Asegúrate de tener este setter
    // Asegúrate de tener este getter
    private String password; // Asegúrate de tener este campo
    private String role;

    // Constructor vacío
    public UserDTO() {
    }

    // Getters y Setters

}
