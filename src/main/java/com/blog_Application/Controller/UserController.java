package com.blog_Application.Controller;

import com.blog_Application.DTO.UserDto;
import com.blog_Application.Entity.User;
import com.blog_Application.Service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController
{

  @Autowired
  UserService userService;
  @RequestMapping(value="/user/add",method=RequestMethod.POST)
  public RequestEntity<User> addUser(UserDto userdto)
  {
         userService.addUser(userdto);
         return new RequestEntity<>(HttpMethod.POST,null);
  }

  @RequestMapping(value="/user/getAll",method= RequestMethod.GET)
  public ResponseEntity<List<User>> getAllUser()
  {
      List<User> users=userService.getAllUser();
      return new ResponseEntity<>(users,HttpStatus.valueOf("got all users"));
  }


    @DeleteMapping("/delete/{Id}")
    public RequestEntity<User> deleteUser(@PathVariable(value="Id") Integer id)
    {
        userService.deleteUser(id);
        return new RequestEntity<>(HttpMethod.DELETE,null);
    }
}
