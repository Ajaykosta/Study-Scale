package com.studyscale.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.studyscale.main", "com.studyscale.controllers", "com.studyscale.service",
		"com.studyscale.repository" })
public class StudyScaleApplication {
	public static void main(String[] args) {
		SpringApplication.run(StudyScaleApplication.class, args);
	}
}