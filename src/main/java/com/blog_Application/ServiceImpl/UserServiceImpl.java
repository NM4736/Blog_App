package com.blog_Application.ServiceImpl;

import com.blog_Application.DAO.UserRepository;
import com.blog_Application.DTO.UserDto;
import com.blog_Application.Entity.User;
import com.blog_Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public void addUser(UserDto userdto)
    {
        User user= new User();
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setAddress(user.getAddress());
        userRepository.save(user);

    }

    @Override
    public void deleteUser(Integer id) {

        User user= userRepository.findById(id).orElse(null);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users= userRepository.findAll(Sort.by("name"));
        return users;
    }
}
