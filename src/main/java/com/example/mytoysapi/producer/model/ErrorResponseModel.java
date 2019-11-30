package com.example.mytoysapi.producer.model;

import org.apache.http.HttpStatus;

public class ErrorResponseModel {
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public ErrorResponseModel(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
