package com.diwakar.SpringBootProject.controller;

import com.diwakar.SpringBootProject.dto.AuthResponse;
import com.diwakar.SpringBootProject.dto.LoginRequest;
import com.diwakar.SpringBootProject.dto.RegisterRequest;
import com.diwakar.SpringBootProject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(service.login(request));
    }
}
