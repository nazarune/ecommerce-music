package com.nlksnc.api.exception.wrapper;

public class PasswordException extends RuntimeException {
    public PasswordException() {
        super();
    }
    public PasswordException(String message) {
        super(message);
    }
    public PasswordException(String message, Throwable cause) {
        super(message, cause);
    }
    public PasswordException(Throwable cause) {
        super(cause);
    }
}
