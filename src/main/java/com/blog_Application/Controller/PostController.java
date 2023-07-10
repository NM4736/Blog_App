package com.blog_Application.Controller;

import com.blog_Application.DTO.PostDTO;
import com.blog_Application.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable(value="userId") Integer userId,
                                              @PathVariable(value="categoryId") Integer categoryId) {
        PostDTO createdPost = postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int postId) {
        PostDTO post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PostDTO>> getPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable int postId, @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(postId, postDTO);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("Post delete Successfully",HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable(value="userId") Integer userId)
    {
       List<PostDTO> postDTO= postService.getPostByUser(userId);
        return new ResponseEntity<>(postDTO,HttpStatus.FOUND);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable(value="categoryId") Integer Id)
    {
        List<PostDTO> postDTO= postService.getPostByCategory(Id);
        return new ResponseEntity<>(postDTO,HttpStatus.FOUND);
    }
}
