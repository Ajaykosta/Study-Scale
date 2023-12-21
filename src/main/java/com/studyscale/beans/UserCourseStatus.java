package com.studyscale.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserCourseStatus {
	private int serialNumber;
	private String subjectName;
	private String unitName;
	private int totalPercent;
	private int total;
	private int completed;
	private int remaining;
	float result;
//	private Date targetDate;

}
