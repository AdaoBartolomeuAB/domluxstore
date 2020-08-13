package com.domluxstore.exception;

import lombok.Getter;

@Getter
public class RecourceNotFoundException extends RuntimeException{

    private String detail;

    public RecourceNotFoundException() {
        super();
    }

    public RecourceNotFoundException(String message) {
        super(message);
    }

    public RecourceNotFoundException(String message,String detail) {
        super(message);
        this.detail=detail;
    }

    public RecourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecourceNotFoundException(Throwable cause) {
        super(cause);
    }

    protected RecourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
