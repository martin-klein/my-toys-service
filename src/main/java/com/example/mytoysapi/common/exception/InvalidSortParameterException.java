package com.example.mytoysapi.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown if a sort parameter not supported by this Service is used or if the sort string is malformed.
 */
public class InvalidSortParameterException extends MyToysServiceException {

    public InvalidSortParameterException(int statusCode, String message) {
        super(message, HttpStatus.valueOf(statusCode));
    }
}
