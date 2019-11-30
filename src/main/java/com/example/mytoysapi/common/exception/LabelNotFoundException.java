package com.example.mytoysapi.common.exception;

import org.springframework.http.HttpStatus;

public class LabelNotFoundException extends MyToysServiceException {

    public LabelNotFoundException(int statusCode, String message) {
        super(message, HttpStatus.valueOf(statusCode));
    }
}
