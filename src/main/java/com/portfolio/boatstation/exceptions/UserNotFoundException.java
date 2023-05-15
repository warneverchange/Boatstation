package com.portfolio.boatstation.exceptions;

import com.portfolio.boatstation.entities.security.User;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    @Getter
    private User user;
    public UserNotFoundException(String message) {
        super(message);
    }
}
