package com.example.quotes.quotes.exception;

public class AuthorizationHeaderValueException extends RuntimeException {

    public AuthorizationHeaderValueException() {
        super();
    }

    public AuthorizationHeaderValueException(String message) {
        super(message);
    }

}
