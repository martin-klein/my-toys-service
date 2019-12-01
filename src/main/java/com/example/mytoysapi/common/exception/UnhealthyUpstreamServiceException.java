package com.example.mytoysapi.common.exception;

import org.springframework.http.HttpStatus;

/**
 * Is thrown if the circuit is open or the timeout is reached.
 */
public class UnhealthyUpstreamServiceException extends MyToysServiceException {
    public UnhealthyUpstreamServiceException(int statusCode, String message) {
        super(message, HttpStatus.valueOf(statusCode));
    }
}
