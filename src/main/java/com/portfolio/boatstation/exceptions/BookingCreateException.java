package com.portfolio.boatstation.exceptions;

import com.portfolio.boatstation.requests.BookingCreateBody;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Data
public class BookingCreateException extends RuntimeException{
    private final BookingCreateBody bookingCreateBody;

    public BookingCreateException(String message, BookingCreateBody bookingCreateBody) {
        super(message);
        this.bookingCreateBody = bookingCreateBody;
    }
}
