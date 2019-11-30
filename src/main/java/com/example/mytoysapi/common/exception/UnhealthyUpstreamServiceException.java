package com.example.mytoysapi.common.exception;

public class UnhealthyUpstreamServiceException extends RuntimeException {
    public UnhealthyUpstreamServiceException(String message) {
        super(message);
    }
}
