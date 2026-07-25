package com.diwakar.SpringBootProject.dto;

import com.diwakar.SpringBootProject.model.Users;
import lombok.Data;

@Data
public class RegisterRequest   {
    private String username;
    private String password;
    private String email;
}
