package com.studyscale.service;

import java.util.List;

import com.studyscale.beans.Course;

public interface CourseServiceInterface {
	public void addCourse(Course course);

	public Course getCourseByCourseName(String courseName);

	public List<Course> showCourse();
}