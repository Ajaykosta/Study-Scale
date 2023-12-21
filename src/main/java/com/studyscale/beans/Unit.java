package com.studyscale.beans;

import java.util.HashSet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Unit {
	private String UnitName;
	private HashSet<String> topicsList;
	private HashSet<String> completedTopicList;

	public Unit() {
		this.completedTopicList = new HashSet<String>();
	}
}