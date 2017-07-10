package com.example.quotes.quotes.exception;

public class AuthorizationHeaderNotFoundException extends RuntimeException {

    public AuthorizationHeaderNotFoundException() {
        super();
    }

    public AuthorizationHeaderNotFoundException(String message) {
        super(message);
    }

}
