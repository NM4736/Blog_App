package com.blog_Application.ServiceImpl;

import com.blog_Application.DAO.UserRepository;
import com.blog_Application.DTO.UserDto;
import com.blog_Application.Entity.User;
import com.blog_Application.Exception.ResourceNotFoundException;
import com.blog_Application.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    Logger log= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Override
    public void addUser(UserDto userdto)
    {
        User user= new User();
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setAddress(userdto.getAddress());
        userRepository.save(user);

    }

    @Override
    public void deleteUserByName(String name ) {

        User user= userRepository.findByName(name).orElseThrow(()-> new ResourceNotFoundException("User with userName: ",name," does not exist"));
        userRepository.deleteByUserName(name);
    }
    @Override
    public void deleteUserById(Integer id ) {
        // first check user present or not
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with userId: ",id.toString()," not found"));
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Integer id) {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with userId: ",id.toString()," not found"));
        return user;
    }


    public User updateUserById(UserDto userDto,Integer id) {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with userId ",id.toString()," not found"));
        if(userDto.getAddress()!=null)
        user.setAddress(userDto.getAddress());
        if(userDto.getEmail()!=null)
        user.setEmail(userDto.getEmail());
        if(userDto.getName()!=null)
        user.setName(userDto.getName());

        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> users= userRepository.findAll();
        return users;
    }
}
