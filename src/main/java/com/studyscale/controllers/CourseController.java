package com.studyscale.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studyscale.beans.Course;
import com.studyscale.service.CourseService;
import com.studyscale.service.UserService;

@RestController
public class CourseController {
	@Autowired
	CourseService courseService;
	@Autowired
	UserService userService;

	@PostMapping("/addcourse")
	public Course addCourse(@RequestBody Course course) {
		courseService.addCourse(course);
		System.out.println(course.getSubjectList());
		return course;
	}

	@PostMapping("/showallcourse")
	public List<Course> showAllCourse() {
		return courseService.showCourse();
	}
}