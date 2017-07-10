package com.example.quotes.quotes.util;

import com.example.quotes.quotes.exception.AuthorizationHeaderValueException;

public final class TokenProcessor {

    private TokenProcessor() {
        throw new UnsupportedOperationException("This should not be accessed");
    }

    public static String getTokenValueFromHeaderString(String headerValue) {
        String[] bearerAndTokenValues = headerValue.split("=");
        if (bearerAndTokenValues.length != 2) {
            throw new AuthorizationHeaderValueException("Bearer value not present in the Authorization header value");
        }
        return bearerAndTokenValues[1];
    }

}
