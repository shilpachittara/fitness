package com.iot.xeynse.exception;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public abstract class XeException extends RuntimeException {

    private static final long serialVersionUID = 5642234219567498468L;

    private final HttpStatus httpStatus;
    private ErrorCode errorCode;

    protected XeException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    protected XeException withErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Optional<ErrorCode> getErrorCode() {
        return Optional.ofNullable(errorCode);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
