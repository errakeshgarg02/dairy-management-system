package com.rakesh.dairy.exception;

public class DairyException extends Exception {

	private static final long serialVersionUID = -5676307735781878835L;
	
	public DairyException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public DairyException(String message) {
		super(message);
	}

}
