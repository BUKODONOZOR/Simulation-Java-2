package com.Main.Simulation.repository;

import com.Main.Simulation.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}