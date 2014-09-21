package com.orangeandbronze.schoolreg.domain;

public abstract class DomainException extends RuntimeException {

	DomainException(String message) {
		super(message);
	}

	DomainException(Throwable cause) {
		super(cause);
	}

	DomainException(String message, Throwable cause) {
		super(message, cause);
	}

	DomainException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
