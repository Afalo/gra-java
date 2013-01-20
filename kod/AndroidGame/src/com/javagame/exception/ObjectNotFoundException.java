package com.javagame.exception;

public class ObjectNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(){
		super();
	}
	
	public ObjectNotFoundException(String message) {
		super(message);		
	}

}
