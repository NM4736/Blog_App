package com.blog_Application.Controller;

import com.blog_Application.DTO.PostDTO;
import com.blog_Application.Response.PostResponse;
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
    public ResponseEntity<PostResponse> getPosts(
            @RequestParam(value="pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value="pageSize",defaultValue = "5",required = false)Integer pageSize) {
      /* List<PostDTO> posts = postService.getAllPosts(pageNo,pageSize);
        return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);*/
        PostResponse postResponse=postService.getAllPosts(pageNo,pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
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

   /* @GetMapping("/user/{userId}")
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
    }*/


    @GetMapping("/user/{userId}")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable(value="userId") Integer userId,

        @RequestParam(value="pageNo", defaultValue = "0", required = false) Integer pageNo,
        @RequestParam(value="pageSize",defaultValue = "5",required = false)Integer pageSize) {

        PostResponse postResponse=postService.getPostByUser(userId,pageNo,pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable(value="categoryId") Integer Id,
                                                          @RequestParam(value="pageNo",defaultValue = "0",required = false)Integer pageNo,
                                                          @RequestParam(value="pageSize",defaultValue = "2",required = false)Integer pageSize )
    {
        PostResponse postResponse= postService.getPostByCategory(Id,pageNo,pageSize);
        return new ResponseEntity<>(postResponse,HttpStatus.FOUND);
    }
}
