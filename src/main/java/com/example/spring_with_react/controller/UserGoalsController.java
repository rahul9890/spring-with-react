package com.example.spring_with_react.controller;

import com.example.spring_with_react.model.request.create.goals.UserGoalsReq;
import com.example.spring_with_react.model.response.create.UserGoalsResp;
import com.example.spring_with_react.service.UserGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/goals")
@CrossOrigin
public class UserGoalsController {

    private UserGoalsService userGoalsService;

    public UserGoalsController(@Autowired UserGoalsService userGoalsService) {
        this.userGoalsService = userGoalsService;
    }


    @PutMapping
    public ResponseEntity<String> createUserGoals(@RequestBody UserGoalsReq userGoalsReq) {

        boolean userGoalCreated = userGoalsService.createuserGoals(userGoalsReq);

        if (userGoalCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User goal created successfully.");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user goal.");
    }

    @GetMapping
    public ResponseEntity<List<UserGoalsResp>> getGoalsForUser(@RequestParam UUID userId){


        return ResponseEntity.ok(userGoalsService.getGoalsForUser(userId));
    }
}