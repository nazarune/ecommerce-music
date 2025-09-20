package com.nlksnc.api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
@Builder
public class ExceptionMessage {
    private HttpStatus status;
    private String message;
    private ZonedDateTime timestamp;
}
