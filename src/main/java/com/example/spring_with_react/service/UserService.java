package com.example.spring_with_react.service;

import com.example.spring_with_react.model.request.createUser.User;
import com.example.spring_with_react.model.response.createUser.UserResponse;
import com.example.spring_with_react.repository.UserRepository;
import com.example.spring_with_react.utils.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
