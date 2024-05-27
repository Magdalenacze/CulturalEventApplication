package com.example.culturaleventapplication.culturalevent.exception;

public class CulturalEventServiceException extends RuntimeException {

    public CulturalEventServiceException(String message) {
        super(message);
    }

    public CulturalEventServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
