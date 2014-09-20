package com.orangeandbronze.schoolreg.domain;

public class EnlistmentConflictException extends RuntimeException {


	public EnlistmentConflictException(String message) {
		super(message);
	}

	public EnlistmentConflictException(Throwable cause) {
		super(cause);
	}

	public EnlistmentConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnlistmentConflictException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
