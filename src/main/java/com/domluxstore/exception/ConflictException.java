package com.domluxstore.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ConflictException extends RuntimeException {

    private Map<String,String> details;

    public ConflictException() {
    }

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message,Map<String,String> details) {
        super(message);
        this.details=details;
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }

    public ConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
