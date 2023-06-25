package com.blog_Application.Controller;

import com.blog_Application.DTO.UserDto;
import com.blog_Application.Entity.User;
import com.blog_Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class UserController
{

  @Autowired
  UserService userService;
  @RequestMapping(value="/user/add/",method=RequestMethod.POST)
  public ResponseEntity<String> addUser(RequestEntity<UserDto> userdto)
  {
         userService.addUser(userdto.getBody());
         String body= userdto.getBody().toString();
         String method=userdto.getMethod().toString();
         String type=userdto.getType().toString();
         String url=userdto.getUrl().toString();
         return new ResponseEntity<>(body+" "+method+" "+ type+" "+url, HttpStatus.CREATED);
  }

  @RequestMapping(value="/user/getAll",method= RequestMethod.GET)
  public ResponseEntity<List<User>> getAllUser()
  {
      List<User> users=userService.getAllUser();
      HttpHeaders headers = new HttpHeaders();
      headers.add("Custom-Header", "Demo-Value");
      return new ResponseEntity<>(users,headers,HttpStatus.valueOf(200));
  }

@RequestMapping(value="/user/id/{id}", method=RequestMethod.GET)
public ResponseEntity<User> getUserById(@PathVariable(value="id") Integer id)
{
    User user=userService.getUserById(id);
    return new ResponseEntity<>(user,HttpStatus.OK);
}

@RequestMapping(value="user/update/id/{id}", method=RequestMethod.PUT)
public ResponseEntity<User> updateUserById( @RequestBody UserDto userDto, @PathVariable(value="id") Integer ID)
{
    User user= userService.updateUserById(userDto ,ID);
    return new ResponseEntity<>(user,HttpStatus.OK);

}


    @DeleteMapping("/delete/id/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value="Id") Integer id)
    {
        userService.deleteUserById(id);
        return new ResponseEntity<>("user with id: "+id+" got delete",HttpStatus.OK);
    }
    @DeleteMapping("/delete/name/{name}")
    public ResponseEntity<String> deleteUserByName(@PathVariable(value="name") String name)
    {
        userService.deleteUserByName(name);
        return new ResponseEntity<>("user with id: "+name+" got delete",HttpStatus.OK);
    }
}