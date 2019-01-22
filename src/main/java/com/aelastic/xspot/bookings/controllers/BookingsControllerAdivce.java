package com.aelastic.xspot.bookings.controllers;

import com.aelastic.xspot.bookings.models.response.ErrorDetailsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class BookingsControllerAdivce extends ResponseEntityExceptionHandler {


    public static final String VALIDATION_FAILED = "Validation failed";

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
            ErrorDetailsResponse errorDetails = ErrorDetailsResponse.builder()
                    .date(new Date())
                    .message(VALIDATION_FAILED)
                    .exception(ex.getBindingResult().toString()).build();
            return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
        }

    }
