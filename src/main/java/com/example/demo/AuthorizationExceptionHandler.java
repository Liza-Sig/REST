package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthorizationExceptionHandler {

    @ExceptionHandler(InvalidCredentials.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidCredentials(InvalidCredentials exception) {
        System.out.println("InvalidCredentials exception: " + exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(UnauthorizedUser.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedUser(UnauthorizedUser exception) {
        System.out.println("UnauthorizedUser exception: " + exception.getMessage());
        return exception.getMessage();
    }
}
