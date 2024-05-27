package com.example.culturaleventapplication.culturalevent.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CulturalEventControllerAdvice {

    @ExceptionHandler(com.example.culturaleventapplication.culturalevent.exception.CulturalEventException.class)
    public ResponseEntity<ErrorResponse> handleCulturalEventException(com.example.culturaleventapplication.culturalevent.exception.CulturalEventException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(com.example.culturaleventapplication.culturalevent.exception.CulturalEventServiceException.class)
    public ResponseEntity handleCulturalEventServiceException(com.example.culturaleventapplication.culturalevent.exception.CulturalEventServiceException e){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse(e.getMessage()));
    }

    @Getter
    @AllArgsConstructor
    static class ErrorResponse {
        private String reason;
    }
}
