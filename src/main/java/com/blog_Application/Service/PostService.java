package com.blog_Application.Service;

import com.blog_Application.DTO.PostDTO;
import com.blog_Application.Entity.Post;
import com.blog_Application.Response.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO,Integer userId, Integer CategoryId);

    PostDTO getPostById(int postId);

    PostDTO updatePost(int postId, PostDTO postDTO);

    void deletePost(int postId);

   // List<PostDTO> getAllPosts(Integer pageNo, Integer pageSize);
   PostResponse getAllPosts(Integer pageNo, Integer pageSize);

   // List<PostDTO> getPostByUser(Integer userId);
    PostResponse getPostByUser(Integer userId, Integer PageNo, Integer PageSize);
    // List<PostDTO> getPostByCategory(Integer categoryId);

    PostResponse getPostByCategory(Integer userId, Integer PageNo, Integer PageSize);

}
