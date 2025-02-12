package org.company.feignclientdemo.exception;

public class UnexpectedErrorException extends RuntimeException {

    public UnexpectedErrorException(String message) {
        super(message);
    }

    public UnexpectedErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
