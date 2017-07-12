package com.example.quotes.quotes.exception;

import com.example.quotes.quotes.model.ErrorCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private ErrorCode errorCode;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ApplicationException withErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
