package com.studyscale.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyscale.beans.User;
import com.studyscale.dto.LoginData;
import com.studyscale.exceptions.InvalidPasswordException;
import com.studyscale.exceptions.UserNotFoundException;
import com.studyscale.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public User login(@RequestBody LoginData loginData) {
		User user = null;
		try {
			user = userService.login(loginData.getId(), loginData.getPassword());
		} catch (UserNotFoundException unfe) {
			unfe.getCause();
		} catch (InvalidPasswordException ipe) {
			ipe.getCause();
		}
		return user;
	}

	@PostMapping("/assigncourse")
	public String assignCourse(String courseName, int userId) {
		userService.assignCourse(courseName, userId);
		return "Course Added Successfully";
	}

	@PostMapping("/updatecompletedtopic")
	public String updateCompletedTopic(@RequestParam List<String> completedTopicList, String courseName,
			String subjectName, String unitName, int userId) {
		userService.updateCompletedTopic(completedTopicList, courseName, subjectName, unitName, userId);
		return "Updated successfully";
	}
}