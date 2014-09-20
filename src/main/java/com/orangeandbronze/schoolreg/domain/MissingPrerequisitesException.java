package com.orangeandbronze.schoolreg.domain;

public class MissingPrerequisitesException extends RuntimeException {


	public MissingPrerequisitesException(String message) {
		super(message);
	}

	public MissingPrerequisitesException(Throwable cause) {
		super(cause);
	}

	public MissingPrerequisitesException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingPrerequisitesException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
