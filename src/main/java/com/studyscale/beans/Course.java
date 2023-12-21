package com.studyscale.beans;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class Course {
	private String courseName;
	private Date courseStartedDate;
	private Date courseEndingDate;
	private List<Subject> subjectList;
}