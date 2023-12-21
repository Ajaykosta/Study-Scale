package com.studyscale.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.studyscale.beans.Course;
import com.studyscale.beans.Subject;
import com.studyscale.beans.Unit;

@Service
public class CourseService implements CourseServiceInterface {
	private List<Course> courseList = new ArrayList<>();

	public void addCourse(Course course) {
		courseList.add(course);
	}

	public List<Course> showCourse() {
		return courseList;
	}

	public Course getCourseByCourseName(String courseName) {
		Course newCourse = null;
		for (Course course : courseList) {
			if (course.getCourseName().equals(courseName)) {
				newCourse = course;
			}
		}
		return newCourse;
	}

	public Subject getSubjectBySubjectName(String courseName, String subjectName) {
		Subject subject = null;
		for (Course course : courseList) {
			if (course.getCourseName().equals(courseName)) {
				for (Subject newSubject : course.getSubjectList()) {
					if (newSubject.equals(subjectName)) {
						subject = newSubject;
					}
				}
			}
		}
		return subject;
	}

	public Unit getUnitByUnitName(String courseName, String subjectName, String unitName) {
		Unit unit = null;
		for (Course course : courseList) {
			if (course.getCourseName().equals(courseName)) {
				for (Subject newSubject : course.getSubjectList()) {
					if (newSubject.equals(subjectName)) {
						for (Unit newUnit : newSubject.getUnitList()) {
							if (newUnit.equals(unitName)) {
								unit = newUnit;
							}
						}
					}
				}
			}
		}
		return unit;
	}

	public String getTopicByTopicName(String courseName, String subjectName, String unitName, String topicName) {
		String topic = null;
		for (Course course : courseList) {
			if (course.getCourseName().equals(courseName)) {
				for (Subject newSubject : course.getSubjectList()) {
					if (newSubject.equals(subjectName)) {
						for (Unit newUnit : newSubject.getUnitList()) {
							if (newUnit.equals(unitName)) {
								for (String newTopic : newUnit.getTopicsList()) {
									if (newTopic.equals(topicName)) {
										topic = newTopic;
									}
								}
							}
						}
					}
				}
			}
		}
		return topic;
	}
}