package com.blog_Application.Controller;

import com.blog_Application.DTO.UserDto;
import com.blog_Application.Service.JWTTokenHelper;
import com.blog_Application.Service.JwtAuthResponse;
import com.blog_Application.Service.JwtAuthenticationRequest;
import com.blog_Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthenticationRequest request ) throws Exception {
           this.authenticate(request.getUsername(),request.getPassword());
           UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
           String token =this.jwtTokenHelper.generateToken(userDetails);
           JwtAuthResponse response= new JwtAuthResponse();
           response.setToken(token);
           return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    public void authenticate(String userName, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userName,password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        }
        catch(DisabledException e)
        {
            System.out.println("invalid credential");
            throw new Exception("user is disabled");
        }


    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto)
    {
        UserDto userDto1= this.userService.registerUser(userDto);

        return new ResponseEntity<>(userDto1,HttpStatus.CREATED);
    }
}
