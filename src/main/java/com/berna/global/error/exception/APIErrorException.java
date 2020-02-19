package com.berna.global.error.exception;

@SuppressWarnings("serial")
public class APIErrorException extends RuntimeException {
	public APIErrorException(String message) {
        super(message);
    }
}
