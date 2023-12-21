package com.studyscale.exceptions;

import com.studyscale.util.Constants;

public class CannotBeNullException extends Exception {
	public CannotBeNullException() {
		System.out.println(Constants.CANNOT_BE_NULL);
	}
}
