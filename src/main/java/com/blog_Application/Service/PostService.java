package com.blog_Application.Service;

import com.blog_Application.DTO.PostDTO;
import com.blog_Application.Entity.Post;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO,Integer userId, Integer CategoryId);

    PostDTO getPostById(int postId);

    PostDTO updatePost(int postId, PostDTO postDTO);

    void deletePost(int postId);

    List<PostDTO> getAllPosts();

    List<PostDTO> getPostByUser(Integer userId);
    List<PostDTO> getPostByCategory(Integer categoryId);

}
