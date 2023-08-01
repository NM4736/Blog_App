package com.blog_Application.Service;

import com.blog_Application.DAO.UserRepository;
import com.blog_Application.Entity.User;
import com.blog_Application.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {

        //get user by userName
        User user= (userRepository.findByName(useremail).orElseThrow(() -> new ResourceNotFoundException("useremail", useremail + "")));
        return user;
    }
}
