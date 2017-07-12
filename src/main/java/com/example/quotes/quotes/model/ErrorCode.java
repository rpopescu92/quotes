package com.example.quotes.quotes.model;

import lombok.Getter;

@Getter
public class ErrorCode {

    private int errorCode;
    private String message;

    public static final ErrorCode USER_ALREADY_EXISTS = new ErrorCode(1411, "User already exists");
    public static final ErrorCode USER_DOES_NOT_EXIST = new ErrorCode(1412, "User does not exist");
    public static final ErrorCode USER_NOT_LOGGED_IN = new ErrorCode(1413, "User not logged in");

    private ErrorCode() {
    }

    private ErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
