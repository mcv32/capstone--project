package com.example.server.Controllers;

import com.example.server.Exceptions.*;
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

    @ExceptionHandler(value= EmailDoesntExist.class)
    public ResponseEntity<ApiError> handleEmailDoesntExistException(){
        ApiError error = new ApiError(400, "Email Doesnt Exist", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= IncorrectPassword.class)
    public ResponseEntity<ApiError> handleIncorrectPassword(){
        ApiError error = new ApiError(400, "Incorrect Password", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value= EmailAlreadyRegistered.class)
    public ResponseEntity<ApiError> handleEmailAlreadyRegistered(){
        ApiError error = new ApiError(400, "Email already registered", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= InvalidEmail.class)
    public ResponseEntity<ApiError> handleInvalidEmail(){
        ApiError error = new ApiError(400, "Invalid email", new Date());
        return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
    }
}
