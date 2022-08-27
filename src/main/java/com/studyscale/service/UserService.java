package com.studyscale.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyscale.beans.Course;
import com.studyscale.beans.Subject;
import com.studyscale.beans.Unit;
import com.studyscale.beans.User;
import com.studyscale.exceptions.CannotBeNullException;
import com.studyscale.exceptions.InvalidPasswordException;
import com.studyscale.exceptions.UserEMailExistException;
import com.studyscale.exceptions.UserNotFoundException;

@Service
public class UserService {
	@Autowired
	CourseService courseService;
	Map<Integer, User> userList = new HashMap<>();

	public void addUser(String name, String eMail, String password)
			throws UserEMailExistException, CannotBeNullException {
		if (name.equals("") || eMail.equals("") || password.equals("")) {
			throw new CannotBeNullException();
		} else if (!eMailChecker(eMail)) {
			User user = new User();
			String encryptedPassword = passwordEncrynption(password);
			user.setName(name);
			user.setEMail(eMail);
			user.setPassword(encryptedPassword);
			user.setCourseList(new ArrayList<Course>());
			userList.put(user.getUserId(), user);
		} else {
			throw new UserEMailExistException();
		}
	}

	private String passwordEncrynption(String password) {
		return "Encrypted" + password;
	}

	private boolean eMailChecker(String eMail) {
		boolean eMailRegistered = false;
		for (User user : userList.values()) {
			if (user.getEMail().equalsIgnoreCase(eMail)) {
				eMailRegistered = true;
				break;
			}
		}
		return eMailRegistered;
	}

	public User login(int id, String password) throws UserNotFoundException, InvalidPasswordException {
		User verifiedUser = null;
		if (getUserById(id) != null) {
			verifiedUser = getUserById(id);
			if (!verifiedUser.getPassword().equals(passwordEncrynption(password))) {
				verifiedUser = null;
				throw new InvalidPasswordException();
			}
		} else {
			throw new UserNotFoundException();
		}
		return verifiedUser;
	}

	public User getUserById(int id) {
		User getUser = null;
		for (User user : userList.values()) {
			if (user.getUserId() == id) {
				getUser = user;
				break;
			}
		}
		return getUser;
	}

	public Map<Integer, User> showAllUsers() {
		return userList;
	}

	public void assignCourse(String courseName, int id) {
		User user = getUserById(id);
		Course course = courseService.getCourseByCourseName(courseName);
		System.out.println("assignCourse : " + course);
		user.getCourseList().add(course);
	}

	public void updateCompletedTopic(List<String> completedTopicList, String courseName, String subjectName,
			String unitName, int userId) {
		User user = getUserById(userId);
		for (Course course : user.getCourseList()) {
			if (course.getCourseName().equals(courseName)) {
				for (Subject subject : course.getSubjectList()) {
					if (subject.getSubjectName().equals(subjectName)) {
						for (Unit unit : subject.getUnitList()) {
							if (unit.getUnitName().equals(unitName)) {
								Set<String> validTopics = getValidTopics(unit.getTopicsList(), completedTopicList);
								unit.getCompletedTopicList().addAll(validTopics);
								System.out.println("update: " + course);
								/*
								 * for (String completedTopic : validTopics) {
								 * unit.getCompletedTopicList().add(completedTopic); }
								 */
							}
						}
					}
				}
			}
		}
	}

	private Set<String> getValidTopics(Set<String> topicList, List<String> completedTopicList) {
		Set<String> validTopics = new HashSet<String>();
		for (String completedTopic : completedTopicList) {
			if (topicList.contains(completedTopic)) {
				validTopics.add(completedTopic);
			}
		}
		return validTopics;
	}

}