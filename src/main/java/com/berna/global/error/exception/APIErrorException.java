package com.berna.global.error.exception;

@SuppressWarnings("serial")
public class APIErrorException extends RuntimeException {
	public APIErrorException() {
        super("API CALL FAIL");
    }
}
