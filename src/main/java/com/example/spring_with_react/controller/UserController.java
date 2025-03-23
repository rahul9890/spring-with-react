package com.example.spring_with_react.controller;

import com.example.spring_with_react.model.request.createUser.User;
import com.example.spring_with_react.model.response.createUser.UserResponse;
import com.example.spring_with_react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(@Autowired UserService userService){
        this.userService=userService;
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody User user){

        UserResponse createdUser=userService.createUser(user);

        return ResponseEntity.ok(createdUser);
    }
}
