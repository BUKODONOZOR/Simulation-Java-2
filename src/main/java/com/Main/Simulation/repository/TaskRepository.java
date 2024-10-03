package com.Main.Simulation.repository;

import com.Main.Simulation.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedUser_Id(Long userId);
}
