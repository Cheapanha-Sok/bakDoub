package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class NotFoundAdvice {
    public record RestErrorResponse(int status, String message,
                                    LocalDateTime timestamp) {
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundException.class})
    NotFoundAdvice.RestErrorResponse handleCustomerNotFoundException(
            NotFoundException notFoundException) {
        return new NotFoundAdvice.RestErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                notFoundException.getMessage(),
                LocalDateTime.now());
    }
}
