package com.suif.service;

import com.suif.pojo.entity.User;
import com.suif.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;


    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<String> getUserRolesByUser(Long uid) {
        return userRepository.findUserRoles(uid);
    }
}