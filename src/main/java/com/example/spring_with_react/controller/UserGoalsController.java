package com.example.spring_with_react.controller;

import com.example.spring_with_react.model.request.create.goals.UserGoalsReq;
import com.example.spring_with_react.model.response.create.UserGoalsResp;
import com.example.spring_with_react.service.UserGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/goals")
public class UserGoalsController {

    private UserGoalsService userGoalsService;

    public UserGoalsController(@Autowired UserGoalsService userGoalsService){
        this.userGoalsService=userGoalsService;
    }


    @PutMapping
    public ResponseEntity<UserGoalsResp> createUserGoals(@RequestBody UserGoalsReq userGoalsReq){

        UserGoalsResp userGoalsResp =userGoalsService.createuserGoals(userGoalsReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(userGoalsResp);
    }
}
