package com.studyscale.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyscale.beans.User;
import com.studyscale.beans.UserCourseStatus;
import com.studyscale.dto.LoginData;
import com.studyscale.exceptions.InvalidPasswordException;
import com.studyscale.exceptions.UserNotFoundException;
import com.studyscale.service.UserServiceInterface;

@RestController
public class UserController {
	static Logger log = Logger.getLogger(UserController.class.getName());
	@Autowired
	UserServiceInterface userService;

	@CrossOrigin
	@PostMapping("/login")
	public User login(@RequestBody LoginData loginData) {
		User user = null;
		try {
			log.info("login called with data :" + loginData.toString());
			user = userService.login(loginData.getId(), loginData.getPassword());
		} catch (UserNotFoundException unfe) {
			unfe.getCause();
		} catch (InvalidPasswordException ipe) {
			ipe.getCause();
		}
		return user;
	}

	@CrossOrigin
	@PostMapping("/assigncourse")
	public User assignCourse(String courseName, String userId) {
		System.out.println("=========>" + courseName + "   " + userId);
		return userService.assignCourse(courseName, Integer.parseInt(userId));
	}

	@CrossOrigin
	@PostMapping("/updatecompletedtopic")
	public String updateCompletedTopic(@RequestParam List<String> completedTopicList, String courseName,
			String subjectName, String unitName, int userId) {
		userService.updateCompletedTopic(completedTopicList, courseName, subjectName, unitName, userId);
		return "Updated successfully";
	}

	@CrossOrigin
	@PostMapping("/getuserstatus")
	public List<UserCourseStatus> getUserStatus(int userId) {
		return UserServiceInterface.userCourseStatusList;
	}
}