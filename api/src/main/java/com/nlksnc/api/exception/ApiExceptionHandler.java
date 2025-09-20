package com.nlksnc.api.exception;

import com.nlksnc.api.exception.wrapper.EmailException;
import com.nlksnc.api.exception.wrapper.OrderException;
import com.nlksnc.api.exception.wrapper.PasswordException;
import com.nlksnc.api.exception.wrapper.ProductException;
import com.nlksnc.api.exception.wrapper.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {
            EmailException.class,
            OrderException.class,
            PasswordException.class,
            ProductException.class,
            UserException.class
    })
    public <T extends RuntimeException> ResponseEntity<ExceptionMessage> handleApiException(T e) {
        return new ResponseEntity<>(
                ExceptionMessage.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(e.getMessage())
                        .timestamp(ZonedDateTime.now())
                        .build(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
