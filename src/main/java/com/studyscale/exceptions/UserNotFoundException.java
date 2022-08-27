package com.studyscale.exceptions;

import com.studyscale.util.Constants;

public class UserNotFoundException extends Exception {
	public UserNotFoundException() {
		System.out.println(Constants.USER_NOT_FOUND);
	}
}
