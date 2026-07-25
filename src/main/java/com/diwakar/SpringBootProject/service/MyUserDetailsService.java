package com.diwakar.SpringBootProject.service;

import com.diwakar.SpringBootProject.model.UserPrincipal;
import com.diwakar.SpringBootProject.model.Users;
import com.diwakar.SpringBootProject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Users user =
                repo.findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new UsernameNotFoundException(
                                                "User Not Found"
                                        )
                        );

        return new UserPrincipal(user);

    }

}