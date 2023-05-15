package com.portfolio.boatstation.controllers.exceptionhandlers;

import com.portfolio.boatstation.exceptions.BookingCreateException;
import com.portfolio.boatstation.exceptions.InternalServerErrorException;
import com.portfolio.boatstation.responses.BookingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RentalExceptionHandlerController {
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleInternalServerErrorException() {
    }

    @ExceptionHandler(BookingCreateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BookingResponse> handleBookingCreateException(BookingCreateException ex) {
        return new ResponseEntity<>(new BookingResponse(ex.getMessage(), ex.getBookingCreateBody()), HttpStatus.BAD_REQUEST);
    }
}
