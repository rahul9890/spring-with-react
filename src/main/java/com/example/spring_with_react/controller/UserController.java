package com.example.spring_with_react.controller;

import com.example.spring_with_react.model.request.authenticateUser.UserAuthenticationReq;
import com.example.spring_with_react.model.request.createUser.RegisterUserReq;
import com.example.spring_with_react.model.response.createUser.UserResponse;
import com.example.spring_with_react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService userService){
        this.userService=userService;
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody RegisterUserReq registerUserReq){

        UserResponse createdUser=userService.createUser(registerUserReq);

        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/authentication")
    public boolean authenticateUser(@RequestBody UserAuthenticationReq userAuthenticationReq){
        boolean authenticatedUser=userService.authenticateUser(userAuthenticationReq);
        return authenticatedUser;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers(){
        List<UserResponse> userResponses=userService.findAllUsers();

        return ResponseEntity.ok(userResponses);
    }
}
