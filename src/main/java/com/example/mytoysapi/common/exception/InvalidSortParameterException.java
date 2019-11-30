package com.example.mytoysapi.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidSortParameterException extends MyToysServiceException {

    public InvalidSortParameterException(int statusCode, String message) {
        super(message, HttpStatus.valueOf(statusCode));
    }
}
