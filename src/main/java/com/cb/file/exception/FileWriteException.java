package com.cb.file.exception;

public class FileWriteException extends RuntimeException {

	private static final long serialVersionUID = 3708999292651265048L;

	public FileWriteException(Exception e) {
		super(e);
	}

}
