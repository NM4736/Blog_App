package com.blog_Application.Controller;

import com.blog_Application.DTO.CommentDto;
import com.blog_Application.Service.CommentService;
import com.blog_Application.Service.ResponseWo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;

@Controller
@RequestMapping("/comment/")

public class CommentController {


    @Autowired
    CommentService commentService;
    @PostMapping(value="/post/{id}/{userId}")
    public @ResponseBody ResponseWo addNewComment(@RequestBody CommentDto commentDto,
                                                  @PathVariable(value="id") Integer id,
                                                  @PathVariable(value="userId")Integer userId)
    {
        ResponseWo responseWo= new ResponseWo();
        commentService.addNewComment(commentDto,id,userId,responseWo);
        return responseWo;
    }

}
