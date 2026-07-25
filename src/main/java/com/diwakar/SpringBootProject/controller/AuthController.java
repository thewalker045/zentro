package com.diwakar.SpringBootProject.controller;

import com.diwakar.SpringBootProject.dto.RegisterRequest;
import com.diwakar.SpringBootProject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        if(!service.register(request)) return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body("User cannot be registered");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User created successfully");

    }
}
