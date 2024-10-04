package com.Main.Simulation.entity;

import com.Main.Simulation.utils.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;


@Entity
@Data
public class User extends Audit {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @OneToMany(mappedBy = "assignedUser")
    private Set<Task> assignedTasks;

}

