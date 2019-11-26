package com.app.service.exception;

import org.springframework.http.HttpStatus;

public class NotFoundEntityException extends GlobalException {

    public NotFoundEntityException(int id) {
        super("entity not found com.app.service.exception",HttpStatus.NOT_FOUND, "Cant fond entity by Id: " + id );
    }

    public NotFoundEntityException(String error, String message, HttpStatus status) {
        super(error, status, message );
    }
}
