package com.example.quotes.quotes.exception;

import com.example.quotes.quotes.model.ErrorCode;
import lombok.Getter;

@Getter
public class UserAlreadyExistsException extends RuntimeException {

    private ErrorCode errorCode;

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public UserAlreadyExistsException withErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }

}
