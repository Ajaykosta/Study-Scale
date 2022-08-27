package com.studyscale.exceptions;

import com.studyscale.util.Constants;

public class UserEMailExistException extends Exception {
	public UserEMailExistException() {
		System.out.println(Constants.USER_EMAIL_EXIST);
	}
}