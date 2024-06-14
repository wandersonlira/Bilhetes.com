package com.symplesweb.controller.resource.exception;

public class InvalidUpdateException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	
	public InvalidUpdateException(String msg) {
		super(msg);
	}

}
