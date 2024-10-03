package com.Main.Simulation.controller;


import com.Main.Simulation.dto.UserDTO;
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

    // Endpoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(Role.USER); // By default, users are regular

        User createdUser = userService.createUser(user);
        UserDTO responseUserDTO = new UserDTO();
        responseUserDTO.setId(createdUser.getId());
        responseUserDTO.setUsername(createdUser.getUsername());
        responseUserDTO.setRole(createdUser.getRole().name());

        return ResponseEntity.ok(responseUserDTO);
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDTO userDTO) {
        User user = userService.getUserByUsername(userDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getPassword().equals(userDTO.getPassword())) { // Aquí está el uso del getter
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}
