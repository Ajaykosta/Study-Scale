package com.studyscale.exceptions;

import com.studyscale.util.Constants;

public class InvalidPasswordException extends Exception {
	public InvalidPasswordException() {
		System.out.println(Constants.INVALID_PASSWORD);
	}
}
