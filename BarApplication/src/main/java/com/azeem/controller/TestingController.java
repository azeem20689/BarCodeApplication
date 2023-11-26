package com.azeem.controller;

import javax.naming.ContextNotEmptyException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azeem.exception.ControllerException;

@RestController
public class TestingController {

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String Testing(){
        return "Response From the server for USER only";
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String Testing2(){
        return "Response from the server for ADMIN only";
    }

    @GetMapping("/test")
    public String test() {
    	return "This is test";
    }
    
    @GetMapping("/exception")
    public ResponseEntity<?> exception(){
    	throw new ControllerException();
    }
    
    
    
    
}
