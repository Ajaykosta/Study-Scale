package com.studyscale.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.studyscale.main", "com.studyscale.controllers",
		"com.studyscale.service" })
public class StudyScaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyScaleApplication.class, args);
	}
}