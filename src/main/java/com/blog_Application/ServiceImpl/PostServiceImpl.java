package com.blog_Application.ServiceImpl;

import com.blog_Application.DAO.PostRepository;
import com.blog_Application.DTO.PostDTO;
import com.blog_Application.Entity.Post;
import com.blog_Application.Exception.ResourceNotFoundException;
import com.blog_Application.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post = this.modelMapper.map(postDTO, Post.class);
        postRepository.save(post);
        PostDTO savedPostDTO =this.modelMapper.map(post,PostDTO.class);
        return savedPostDTO;
    }

    @Override
    public PostDTO getPostById(int postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() ->  new ResourceNotFoundException("Post not found with",String.valueOf(postId),"postId"));

        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);// same as model mapper
        return postDTO;
    }

    @Override
    public PostDTO updatePost(int postId, PostDTO postDTO) {
        Post post =postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with",String.valueOf(postId),"postId"));

        if(postDTO.getPostTitle()!=null)
        post.setPostTitle(postDTO.getPostTitle());

        if(postDTO.getPostDescription()!=null)
        post.setPostDescription(postDTO.getPostDescription());

        Post updatedPost = postRepository.save(post);
        PostDTO updatedPostDTO = new PostDTO();
        BeanUtils.copyProperties(updatedPost, updatedPostDTO);  // same as model mapper
        return updatedPostDTO;
    }

    @Override
    public void deletePost(int postId)
    {
        Post post = postRepository.findById(postId)
                .orElseThrow(() ->  new ResourceNotFoundException("Post not found with",String.valueOf(postId),"postId"));
        postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOS = new
                ArrayList<>();
        postDTOS = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
       return postDTOS;
    }

}
