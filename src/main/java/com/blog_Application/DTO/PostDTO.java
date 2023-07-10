package com.blog_Application.DTO;


import com.blog_Application.Entity.Category;
import com.blog_Application.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {


    private String postTitle;
    private String postDescription;
    private String image;
    private Date datePosted;
    private UserDto user;
    private CategoryDto category;

}
