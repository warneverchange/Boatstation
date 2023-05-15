package com.portfolio.boatstation.controllers.exceptionhandlers;

import com.portfolio.boatstation.responses.UserNotFoundResponse;
import com.portfolio.boatstation.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionHandlerController {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody UserNotFoundResponse handleUserNotFound(
            HttpServletRequest request,
            UserNotFoundException ex) {
        return new UserNotFoundResponse(request.getRequestURL().toString(), ex.getUser(), ex.getMessage());
    }
}
