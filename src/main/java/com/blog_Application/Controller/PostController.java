package com.blog_Application.Controller;

import com.blog_Application.DTO.PostDTO;
import com.blog_Application.Response.PostResponse;
import com.blog_Application.Service.FileHandlingService;
import com.blog_Application.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;
    @Value("${project.image}")
    String path;

    @Autowired
    FileHandlingService fileHandlingService;

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
            @RequestParam(value="pageSize",defaultValue = "5",required = false)Integer pageSize,
            @RequestParam(value="sortBy",defaultValue = "postTitle",required = false)String sortBy,
            @RequestParam(value="sortDir",defaultValue = "Asc",required = false)String sortDir){
      /* List<PostDTO> posts = postService.getAllPosts(pageNo,pageSize);
        return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);*/
        PostResponse postResponse=postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
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
        @RequestParam(value="pageSize",defaultValue = "5",required = false)Integer pageSize,
        @RequestParam(value="sortBy",defaultValue = "postTitle",required = false)String sortBy,
        @RequestParam(value="sortDir",defaultValue = "Asc",required = false)String sortDir){

        PostResponse postResponse=postService.getPostByUser(userId,pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable(value="categoryId") Integer Id,
                                                          @RequestParam(value="pageNo",defaultValue = "0",required = false)Integer pageNo,
                                                          @RequestParam(value="pageSize",defaultValue = "2",required = false)Integer pageSize,
                                                          @RequestParam(value="sortBy",defaultValue = "postTitle",required = false)String sortBy,
                                                          @RequestParam(value="sortDir",defaultValue = "Asc",required = false)String sortDir)
    {
        PostResponse postResponse= postService.getPostByCategory(Id,pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postResponse,HttpStatus.FOUND);
    }


    @GetMapping("/search/{keyword}")
    public ResponseEntity<PostResponse> findTitleByKeyword(@PathVariable(value="keyword")String word)
    {
        PostResponse postResponse= postService.findTitleByKeyword(word);
        return new ResponseEntity<>(postResponse,HttpStatus.FOUND);
    }

    @PostMapping("/imageUpload/{postId}")
    public ResponseEntity<String> uploadPostImage(@RequestPart("file") MultipartFile file,@PathVariable(value="postId") Integer postId) throws Exception {

        String fileName=fileHandlingService.uploadFile(path,file);
        PostDTO postdto= getPostById(postId).getBody();
        postdto.setImageName(fileName);
        postdto.setImage(file.getInputStream().toString());
        updatePost(postId,postdto);
    return new ResponseEntity<>("imageUplaoded successfully",HttpStatus.OK);
    }

    @GetMapping(value="/image/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<String> getImageByName(@PathVariable(value="fileName") String fileName,
                                                 HttpServletResponse httpResponse) throws IOException {
     InputStream inputStream=fileHandlingService.getFile(path,fileName);
     httpResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, httpResponse.getOutputStream());

        return new ResponseEntity<>("image fetched successfully",HttpStatus.FOUND);
    }

}
