package com.taxi.service.exception;

public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException() {
    }

    public EntityAlreadyExistException(String message) {
        super(message);
    }

    public EntityAlreadyExistException(String message, Exception cause) {
        super(message, cause);
    }
}
