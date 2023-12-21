package com.studyscale.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyscale.beans.User;
import com.studyscale.exceptions.CannotBeNullException;
import com.studyscale.exceptions.UserEMailExistException;
import com.studyscale.service.UserServiceInterface;
import com.studyscale.util.Constants;

@RestController
public class RegistrationController {

	@Autowired
	UserServiceInterface userService;

	@GetMapping("/healthcheck")
	public String getData() {
		return "Study Scale is running.";
	}

	@CrossOrigin
	@PostMapping("/registration")
	public String userRegistration(String name, String eMail, String password) {
		try {
			int id=userService.addUser(name, eMail, password);
			return Constants.USER_ADDED+id;
		} catch (UserEMailExistException une) {
			une.getCause();
			return Constants.USER_EMAIL_EXIST;
		} catch (CannotBeNullException cbne) {
			cbne.getCause();
			return Constants.CANNOT_BE_NULL;
		}

	}

	@GetMapping("/getuserbyid")
	public User getUserById(int id) {
		return userService.getUserById(id);
	}

	@GetMapping("/showallusers")
	public Map<Integer, User> showAllUsers() {
		return userService.showAllUsers();
	}
}