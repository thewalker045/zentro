package com.diwakar.SpringBootProject.service;

import com.diwakar.SpringBootProject.dto.RegisterRequest;
import com.diwakar.SpringBootProject.model.Users;
import com.diwakar.SpringBootProject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(@RequestBody RegisterRequest request){


        if(repo.existsByEmail(request.getEmail()))
            return false;

        Users user=new Users();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        repo.save(user);

        return true;


    }

}
