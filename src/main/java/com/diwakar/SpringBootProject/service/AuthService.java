package com.diwakar.SpringBootProject.service;

import com.diwakar.SpringBootProject.config.JwtService;
import com.diwakar.SpringBootProject.dto.AuthResponse;
import com.diwakar.SpringBootProject.dto.LoginRequest;
import com.diwakar.SpringBootProject.dto.RegisterRequest;
import com.diwakar.SpringBootProject.model.Users;
import com.diwakar.SpringBootProject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    public boolean register(RegisterRequest request) {

        if (repo.existsByEmail(request.getEmail())) {
            return false;
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER_ROLE");

        repo.save(user);

        return true;
    }


    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Users user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                user.getRole()
        );
    }
}