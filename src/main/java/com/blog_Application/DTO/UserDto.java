package com.blog_Application.DTO;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
public class UserDto {

    private String name;
    private String address;
    private String email;
    private String password;
    private Set<RoleDto> role = new HashSet<>();

}
