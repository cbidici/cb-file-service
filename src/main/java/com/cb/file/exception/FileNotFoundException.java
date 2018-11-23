package com.cb.file.exception;

public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5054108283560896001L;
	
	public FileNotFoundException(String message) {
		super(message);
	}

}
