package com.blog_Application.DTO;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
public class CategoryDto {


    String categoryTitle;
    @NotNull
    String categoryDescription;

}
