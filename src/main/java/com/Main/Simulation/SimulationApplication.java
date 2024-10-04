package com.Main.Simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulationApplication.class, args);
	}

}
