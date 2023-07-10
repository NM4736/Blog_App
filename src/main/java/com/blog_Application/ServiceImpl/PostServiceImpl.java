package com.blog_Application.ServiceImpl;

import com.blog_Application.DAO.CategoryRepository;
import com.blog_Application.DAO.PostRepository;
import com.blog_Application.DAO.UserRepository;
import com.blog_Application.DTO.PostDTO;
import com.blog_Application.Entity.Category;
import com.blog_Application.Entity.Post;
import com.blog_Application.Entity.User;
import com.blog_Application.Exception.ResourceNotFoundException;
import com.blog_Application.Response.PostResponse;
import com.blog_Application.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

                Category category= categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category with ",""+categoryId," not found"));
        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with userId: ",userId.toString()," not found"));

        Post post = new Post();
        post = this.modelMapper.map(postDTO, Post.class);
        post.setDatePosted(new Date());
        post.setImage("deafult.png");
        post.setUser(user);
        post.setCategory(category);
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

        if(postDTO.getImage()!=null)
            post.setImage((postDTO.getImage()));

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
    public PostResponse getAllPosts(Integer pageNo, Integer pageSize) {


        // 1) create object of page means ek page banao uska no. do or batao ki usme kitni post aani chahiye page size ki madad s.
        Pageable p  = PageRequest.of(pageNo,pageSize);
        //2) pass page object to repo means jo upper page banaya h repo ko dedo jisse us page m pagesize itne record
        // likh kr page return kr d
        Page<Post> page= postRepository.findAll(p);
        //3) get post means jo page return hua h upper s uska content read kr lo
        List<Post> posts= page.getContent();

        //List<Post> posts = postRepository.findAll(); before implementing pagination concept we use to directly return all post
        List<PostDTO> postDTOS = new ArrayList<>();
        postDTOS = posts.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        // return PostResponse
        PostResponse postResponse= new PostResponse();
        postResponse.setPostDTOList(postDTOS);
        postResponse.setPageNumber(Integer.valueOf(page.getNumber()));
        postResponse.setPageSize(page.getSize());
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setIsLastPage(page.isLast());


       //return postDTOS;
        return postResponse;
    }

    @Override
    public PostResponse getPostByUser(Integer userId,Integer pageNo, Integer pageSize) {

        Pageable pageObj= PageRequest.of(pageNo,pageSize);
       Page<Post> page= postRepository.getPostByUser(userId,pageObj);
        List<Post> posts= page.getContent();
       List<PostDTO> postDTOs= posts.stream().map( p-> this.modelMapper.map(p, PostDTO.class)).collect(Collectors.toList());

       PostResponse postResponse= new PostResponse();
       postResponse.setPostDTOList(postDTOs);
       postResponse.setPageNumber(page.getNumber());
       postResponse.setPageSize(page.getSize());
       postResponse.setTotalPages(page.getTotalPages());
       postResponse.setIsLastPage(page.isLast());

       return postResponse;
    }


    @Override
    public PostResponse getPostByCategory(Integer categoryId,Integer pageNo, Integer pageSize) {


        Pageable pageObj= PageRequest.of(pageNo,pageSize);
        Page<Post> page= postRepository.getPostByCategory(categoryId,pageObj);
        List<Post> post= page.getContent();
        List<PostDTO> postDTOs= post.stream().map( p-> this.modelMapper.map(p, PostDTO.class)).collect(Collectors.toList());

        PostResponse postResponse= new PostResponse();
        postResponse.setPostDTOList(postDTOs);
        postResponse.setTotalPages(page.getTotalPages());
        postResponse.setPageNumber(page.getNumber());
        postResponse.setPageSize(page.getSize());
        postResponse.setIsLastPage(page.isLast());

        return postResponse;
    }


}
