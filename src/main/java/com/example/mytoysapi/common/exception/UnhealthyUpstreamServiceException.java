package com.example.mytoysapi.common.exception;

import org.springframework.http.HttpStatus;

public class UnhealthyUpstreamServiceException extends MyToysServiceException {
    public UnhealthyUpstreamServiceException(int statusCode, String message) {
        super(message, HttpStatus.valueOf(statusCode));
    }
}
