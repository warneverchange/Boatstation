package com.portfolio.boatstation.excpetions;

import com.portfolio.boatstation.entities.views.security.User;
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
