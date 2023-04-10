package com.portfolio.boatstation.exceptionhandlers;

import com.portfolio.boatstation.errors.UserNotFoundErrorInfo;
import com.portfolio.boatstation.excpetions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionHandlerController {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody UserNotFoundErrorInfo handleUserNotFound(
            HttpServletRequest request,
            UserNotFoundException ex) {
        return new UserNotFoundErrorInfo(request.getRequestURL().toString(), ex.getUser(), ex.getMessage());
    }
}
