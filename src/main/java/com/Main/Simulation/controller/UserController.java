package com.Main.Simulation.controller;

import com.Main.Simulation.dto.response.UserResponseDTO;
import com.Main.Simulation.dto.create.UserCreateDTO;
import com.Main.Simulation.entity.User;
import com.Main.Simulation.service.UserService;
import com.Main.Simulation.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword());
        user.setRole(Role.USER); // Por defecto, rol de usuario normal

        User createdUser = userService.createUser(user);

        // Crear el DTO de respuesta
        UserResponseDTO responseUserDTO = new UserResponseDTO();
        responseUserDTO.setId(createdUser.getId());
        responseUserDTO.setUsername(createdUser.getUsername());
        responseUserDTO.setRole(createdUser.getRole().name());

        return ResponseEntity.ok(responseUserDTO);
    }

    // Endpoint para el login del usuario
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserCreateDTO userCreateDTO) {
        User user = userService.getUserByUsername(userCreateDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getPassword().equals(userCreateDTO.getPassword())) {
            return ResponseEntity.ok("Login Succes");
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
