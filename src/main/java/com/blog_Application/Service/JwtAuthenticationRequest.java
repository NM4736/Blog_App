package com.blog_Application.Service;


import lombok.Data;

@Data
public class JwtAuthenticationRequest {


    private String username;
    private  String password;
}
