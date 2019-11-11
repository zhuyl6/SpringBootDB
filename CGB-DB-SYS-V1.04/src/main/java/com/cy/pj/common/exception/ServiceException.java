package com.cy.pj.common.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = -9085326160255400760L;
	public ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
