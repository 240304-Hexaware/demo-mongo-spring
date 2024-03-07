package com.revature.demomongospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages =
		"{com.revature.demomongospring.controllers},"
		+ "{com.revature.demomongospring.services},"
		+ "{com.revature.demomongospring.repositories}"
)
public class DemoMongoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMongoSpringApplication.class, args);
	}

}
