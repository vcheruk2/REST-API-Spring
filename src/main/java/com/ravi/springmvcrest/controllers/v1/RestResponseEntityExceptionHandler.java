package com.ravi.springmvcrest.controllers.v1;

import com.ravi.springmvcrest.services.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/16/2020 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){
        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
