package com.studyscale.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class User {
	private static int idCount;
	private int userId = ++idCount;
	private String name;
	private String eMail;
	@JsonIgnore
	private String password;
	private List<Course> courseList;
}