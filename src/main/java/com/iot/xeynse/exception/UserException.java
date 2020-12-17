package com.iot.xeynse.exception;

import org.springframework.http.HttpStatus;


public class UserException extends XeException {

    public UserException(final String message, final HttpStatus status) {
        super(message, status);
    }

    @Override
    public UserException withErrorCode(ErrorCode errorCode) {
        return (UserException) super.withErrorCode(errorCode);
    }
}