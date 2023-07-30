package com.blog_Application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String postTitle;
    private String postDescription;
    private String image;
    private String imageName;
    private Date datePosted;
    private UserDto user;
    private CategoryDto category;
    private Set<CommentDto> comment= new HashSet<>();

}
