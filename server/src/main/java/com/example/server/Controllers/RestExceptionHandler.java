package com.example.server.Controllers;

import com.example.server.Exceptions.EmailAlreadyExists;
import com.example.server.Models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value= EmailAlreadyExists.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExistsException() {

        ApiError error = new ApiError(400, "Email Already Exists", new Date());

        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }
}
