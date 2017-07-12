package com.example.quotes.quotes.exception.mapper;

import com.example.quotes.quotes.exception.ApplicationException;
import com.example.quotes.quotes.exception.UserAlreadyExistsException;
import com.example.quotes.quotes.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ApplicationExceptionMapper {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ErrorResponse exception(ApplicationException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(e.getErrorCode().getErrorCode());
        errorResponse.setMessage(e.getErrorCode().getMessage());
        return errorResponse;
    }

}
