package com.blog_Application.ServiceImpl;


import com.blog_Application.DAO.CommentDao;
import com.blog_Application.DAO.PostRepository;
import com.blog_Application.DAO.UserRepository;
import com.blog_Application.DTO.CommentDto;
import com.blog_Application.Entity.Comment;
import com.blog_Application.Entity.Post;
import com.blog_Application.Entity.User;
import com.blog_Application.Exception.ResourceNotFoundException;
import com.blog_Application.Service.CommentService;
import com.blog_Application.Service.ResponseWo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentRepo;
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void addNewComment(CommentDto commentDto, Integer postId, Integer userId, ResponseWo responseWo) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post not found with",postId+""));
        User user=  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found",userId+""));
        Comment comment = new Comment();
        comment.setCommentDesc(commentDto.getCommentDesc());
        comment.setPost(post);
        comment.setUser(user);
        commentRepo.saveAndFlush(comment);
        responseWo.setMessage("new comment added");


    }
}
