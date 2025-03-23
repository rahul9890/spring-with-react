package com.example.spring_with_react.service;

import com.example.spring_with_react.model.request.createUser.User;
import com.example.spring_with_react.model.response.createUser.UserResponse;
import com.example.spring_with_react.repository.UserRepository;
import com.example.spring_with_react.utils.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService (@Autowired UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponse createUser(User user){
        UserEntity userEntity=new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        UserResponse userResponse=new UserResponse();
        UserEntity savedEntity=new UserEntity();
        try {
            savedEntity=userRepository.save(userEntity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        userResponse.setUserId(savedEntity.getId());
        userResponse.setEmail(savedEntity.getEmail());
        userResponse.setName(savedEntity.getName());
        return userResponse;
    }

    public List<UserResponse> findAllUsers() {
        List<UserEntity> userEntities=userRepository.findAll();
        List<UserResponse> userResponseList=userEntities.stream().map(e-> UserResponse.builder().userId(e.getId()).name(e.getName()).email(e.getEmail()).build()).collect(Collectors.toList());

    return userResponseList;
    }
}
