package com.blog_Application.Service;

import com.blog_Application.DTO.CommentDto;

 public interface CommentService {

    void addNewComment(CommentDto commentDto,Integer postId,Integer userId,ResponseWo responseWo);



}
