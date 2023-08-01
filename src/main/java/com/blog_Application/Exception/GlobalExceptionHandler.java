package com.blog_Application.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex)
    {
       String message= ex.getResourceName();
       message+= ex.getValue();
       message+= ex.getResourcefield();
        ApiResponse apiResponse= new ApiResponse(message);
       return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
