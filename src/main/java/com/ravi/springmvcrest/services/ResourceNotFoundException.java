package com.ravi.springmvcrest.services;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 5/16/2020 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
