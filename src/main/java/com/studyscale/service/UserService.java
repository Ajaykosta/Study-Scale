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
import com.studyscale.beans.UserCourseStatus;
import com.studyscale.exceptions.CannotBeNullException;
import com.studyscale.exceptions.InvalidPasswordException;
import com.studyscale.exceptions.UserEMailExistException;
import com.studyscale.exceptions.UserNotFoundException;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	CourseServiceInterface courseService;

	Map<Integer, User> userList = new HashMap<>();
	public List<UserCourseStatus> userCourseStatusList;

	public int addUser(String name, String eMail, String password)
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
			return user.getUserId();
			// userRepo.save(convertUserToUserDAO(user));
		} else {
			throw new UserEMailExistException();
		}
	}

	/*
	 * private UserDAO convertUserToUserDAO(User user) { UserDAO dao = new
	 * UserDAO(); // dao.setId(user.getUserId()); dao.setName(user.getName());
	 * dao.setEmail(user.getEMail()); dao.setPassword(user.getPassword()); return
	 * dao; }
	 */

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

	public User assignCourse(String courseName, int id) {
		User user = getUserById(id);
		Course course = courseService.getCourseByCourseName(courseName);
		System.out.println("assignCourse : " + course);
		if (course != null) {
			user.getCourseList().add(course);
		}
		return user;
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

	public List<UserCourseStatus> getCourseStatus(int userId) {
		User user = getUserById(userId);
		for (Course course : user.getCourseList()) {
			int subjectCount = 0;
			for (Subject subject : course.getSubjectList()) {
				List<UserCourseStatus> unitList = new ArrayList<>();
				UserCourseStatus subjectStatus = new UserCourseStatus();
				subjectStatus.setSerialNumber(++subjectCount);
				subjectStatus.setSubjectName(subject.getSubjectName());
				subjectStatus.setUnitName(null);
				subjectStatus.setTotalPercent(100);
				int unitCount = subject.getUnitList().size();
				int eachUnitPercent = 100 / unitCount;
				int totalTopics = 0;
				int totalCompletedTopics = 0;
				for (Unit unit : subject.getUnitList()) {
					UserCourseStatus unitStatus = new UserCourseStatus();
					int topicCount = unit.getTopicsList().size();
					int completedTopicCount = unit.getCompletedTopicList().size();
					totalTopics = totalTopics + topicCount;
					totalCompletedTopics = totalCompletedTopics + completedTopicCount;
					unitStatus.setSerialNumber(0);
					unitStatus.setSubjectName(null);
					unitStatus.setUnitName(unit.getUnitName());
					unitStatus.setTotalPercent(eachUnitPercent);
					unitStatus.setTotal(topicCount);
					unitStatus.setCompleted(completedTopicCount);
					unitStatus.setRemaining(topicCount - completedTopicCount);
					float unitCompletedPercent = (completedTopicCount / topicCount) * 100;
					unitStatus.setResult(unitCompletedPercent);
					unitList.add(unitStatus);
				}
				int remainingTopics = totalTopics - totalCompletedTopics;
				subjectStatus.setTotal(totalTopics);
				subjectStatus.setCompleted(totalCompletedTopics);
				subjectStatus.setRemaining(remainingTopics);
				float subjectCompletionPercent = (totalCompletedTopics / totalTopics) * 100;
				subjectStatus.setResult(subjectCompletionPercent);
				userCourseStatusList.add(subjectStatus);
				userCourseStatusList.addAll(unitList);
			}
		}
		for (UserCourseStatus userCourseStatus : userCourseStatusList) {
			System.out.println(userCourseStatus + "\n");
		}
		return userCourseStatusList;
	}
}