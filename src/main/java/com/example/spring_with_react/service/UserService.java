package com.example.spring_with_react.service;

import com.example.spring_with_react.model.request.authenticateUser.UserAuthenticationReq;
import com.example.spring_with_react.model.request.createUser.RegisterUserReq;
import com.example.spring_with_react.model.request.update.UpdateUserReq;
import com.example.spring_with_react.model.response.createUser.UserResponse;
import com.example.spring_with_react.repository.UserRepository;
import com.example.spring_with_react.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService (@Autowired UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponse createUser(RegisterUserReq registerUserReq){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserName(registerUserReq.getUserName());
        userEntity.setUserEmail(registerUserReq.getUserEmail());
        userEntity.setUserPassword(registerUserReq.getUserPassword());
        UserResponse userResponse=new UserResponse();
        UserEntity savedEntity=new UserEntity();
        try {
            savedEntity=userRepository.save(userEntity);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        userResponse.setUserId(savedEntity.getUserId());
        userResponse.setUserEmail(savedEntity.getUserEmail());
        userResponse.setUserName(savedEntity.getUserName());
        return userResponse;
    }

    public List<UserResponse> findAllUsers() {
        List<UserEntity> userEntities=userRepository.findAll();
        List<UserResponse> userResponseList=userEntities.stream().map(e-> UserResponse.builder().
                userId(e.getUserId()).userName(e.getUserName()).userEmail(e.getUserEmail()).build()).collect(Collectors.toList());

    return userResponseList;
    }

    public boolean authenticateUser(UserAuthenticationReq userAuthenticationReq) {
        UserEntity userEntity=userRepository.findUserEntityByUserEmail(userAuthenticationReq.getEmail());

        if (userEntity != null
                && userEntity.getUserEmail().equals(userAuthenticationReq.getEmail())
                && userEntity.getUserPassword().equals(userAuthenticationReq.getPassword())) {

            return true;
        }

        return false;
    }

    public UserResponse updateUser(UpdateUserReq updateUserReq) {
        UserEntity userEntity=userRepository.findUserEntityByUserId(updateUserReq.getUserId());
        if(null==userEntity){
            throw new RuntimeException("User Not Found");
        }
        // Update the existing entity instead of creating a new one
        if(null!=updateUserReq.getUserName()){
            userEntity.setUserName(updateUserReq.getUserName());
        }
       if(null!=updateUserReq.getUserEmail()){
           userEntity.setUserEmail(updateUserReq.getUserEmail());
       }


        UserEntity updateUserEntity=userRepository.save(userEntity);
        UserResponse userResponse=new UserResponse();
        userResponse.setUserId(updateUserEntity.getUserId());

        userResponse.setUserName(updateUserEntity.getUserName());
        userResponse.setUserEmail(updateUserEntity.getUserEmail());
        return userResponse;
    }

    public void deleteUserByEmail(String userEmail) {
        userRepository.deleteUserEntityByUserEmail(userEmail);
    }
}
