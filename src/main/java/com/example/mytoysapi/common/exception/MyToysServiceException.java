package com.example.mytoysapi.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Extends the Spring {@link ResponseStatusException} so that the framework ensures the correct status code is returned.
 */
public abstract class MyToysServiceException extends ResponseStatusException {

    public MyToysServiceException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);
    }

}
