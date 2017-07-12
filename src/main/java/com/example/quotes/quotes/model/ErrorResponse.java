package com.example.quotes.quotes.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private int errorCode;
    private String message;
}
