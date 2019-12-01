package com.example.mytoysapi.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Is thrown if the label provided cannot be found.
 */
public class LabelNotFoundException extends MyToysServiceException {

    public LabelNotFoundException(int statusCode, String message) {
        super(message, HttpStatus.valueOf(statusCode));
    }
}
