package com.portfolio.boatstation.controllers.exceptionhandlers;

import com.portfolio.boatstation.exceptions.WatercraftAlreadyExistException;
import com.portfolio.boatstation.responses.WatercraftLogAlreadyExistResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WatercraftExceptionHandler {
    @ExceptionHandler(WatercraftAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<WatercraftLogAlreadyExistResponse> handleWatercraftAlreadyExistException(WatercraftAlreadyExistException ex) {
        return new ResponseEntity<>(new WatercraftLogAlreadyExistResponse(ex.getWatercraftLog(), ex.getMessage()), HttpStatus.CONFLICT);
    }
}
