package com.blog_Application.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
public class CategoryDto {


    String categoryTitle;
    String categoryDescription;

}
