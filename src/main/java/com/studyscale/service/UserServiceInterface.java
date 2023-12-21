package com.studyscale.service;

import java.util.List;
import java.util.Map;

import com.studyscale.beans.User;
import com.studyscale.beans.UserCourseStatus;
import com.studyscale.exceptions.CannotBeNullException;
import com.studyscale.exceptions.InvalidPasswordException;
import com.studyscale.exceptions.UserEMailExistException;
import com.studyscale.exceptions.UserNotFoundException;

public interface UserServiceInterface {
	public static final List<UserCourseStatus> userCourseStatusList = null;

	public int addUser(String name, String eMail, String password)
			throws UserEMailExistException, CannotBeNullException;

	public User login(int id, String password) throws UserNotFoundException, InvalidPasswordException;

	public User getUserById(int id);

	public Map<Integer, User> showAllUsers();

	public User assignCourse(String courseName, int id);

	public void updateCompletedTopic(List<String> completedTopicList, String courseName, String subjectName,
			String unitName, int userId);

	public List<UserCourseStatus> getCourseStatus(int userId);
}