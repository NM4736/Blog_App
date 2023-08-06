package com.blog_Application.Service;

import com.blog_Application.DTO.UserDto;
import com.blog_Application.Entity.User;

import java.util.List;

public interface UserService {
    void addUser(UserDto user);
    void deleteUserById(Integer id);
    void deleteUserByName(String name);
    User getUserById(Integer id);
    User updateUserById(UserDto userDto,Integer id);

    UserDto registerUser(UserDto userDto);

    List<User> getAllUser();
}
