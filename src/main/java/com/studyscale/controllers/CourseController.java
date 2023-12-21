package com.studyscale.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studyscale.beans.Course;
import com.studyscale.service.CourseServiceInterface;

@RestController
public class CourseController {
	@Autowired
	CourseServiceInterface courseService;

	@CrossOrigin
	@PostMapping("/addcourse")
	public Course addCourse(@RequestBody Course course) {
		courseService.addCourse(course);
		System.out.println(course.getSubjectList());
		return course;
	}

	@CrossOrigin
	@GetMapping("/showallcourse")
	public List<Course> showAllCourse() {
		return courseService.showCourse();
	}
}