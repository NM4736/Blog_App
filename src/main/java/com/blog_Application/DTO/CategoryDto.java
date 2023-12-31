package com.blog_Application.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data

public class CategoryDto {


    String categoryTitle;

    @NotEmpty(message = "Category description cannot be null")
    String categoryDescription;

}
