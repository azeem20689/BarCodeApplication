package com.azeem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ApiResponse> controllerExceptionHandler(ControllerException ex) {
        return new ResponseEntity<ApiResponse>(new ApiResponse( ex.getErrocode() , ex.getMessage())
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>(new ApiResponse("602", ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> tokenExpiredException(ExpiredJwtException ex ){
    	return new ResponseEntity<>(new ApiResponse("603","TOKEN IS EXPIRED") , HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(){
    	throw new ControllerException("9999","JWT EXPIRED ...");
    }
    

}
