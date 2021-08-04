package com.unicamp.mc322.lab13.Exceptions;

public class IllegalPersonArgumentException extends IllegalArgumentException {

	private static final long serialVersionUID = -7838680572526932291L;

	public IllegalPersonArgumentException() {
	}

	public IllegalPersonArgumentException(String s) {
		super(s);
	}

	public IllegalPersonArgumentException(Throwable cause) {
		super(cause);
	}

	public IllegalPersonArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

}
