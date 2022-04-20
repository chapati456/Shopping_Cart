package com.springjsp.service;

import com.springjsp.model.User;
import com.springjsp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAllByJQl();
    }

    public User getUserByName(String userName) {
        return userRepository.findByUserNameByJQL(userName);
    }

    public String addUser(String userName, String email, String password) {
        userRepository.saveByJQL(userName,email,password);
        return "User registered sucessfully";
    }
}
