package com.Main.Simulation.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String role;

    // Constructor vacío
    public UserDTO() {
    }

    // Getters y Setters

}
