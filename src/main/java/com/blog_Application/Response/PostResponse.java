package com.blog_Application.Response;

import com.blog_Application.DTO.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostResponse
{
    List<PostDTO> postDTOList;
    Integer pageNumber;
    Integer pageSize;
    Integer totalPages;
    Boolean isLastPage; // kya yhh last page h

}
