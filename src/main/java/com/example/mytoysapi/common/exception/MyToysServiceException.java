package com.example.mytoysapi.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class MyToysServiceException extends ResponseStatusException {

    public MyToysServiceException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }

}
