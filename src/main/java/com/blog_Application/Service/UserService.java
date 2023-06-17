package com.blog_Application.Service;

import com.blog_Application.DTO.UserDto;
import com.blog_Application.Entity.User;

import java.util.List;

public interface UserService {
    void addUser(UserDto user);
    void deleteUser(Integer id);

    List<User> getAllUser();
}
