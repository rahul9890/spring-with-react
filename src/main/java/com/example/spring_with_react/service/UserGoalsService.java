package com.example.spring_with_react.service;

import com.example.spring_with_react.entities.GoalEntity;
import com.example.spring_with_react.model.request.create.goals.UserGoalsReq;
import com.example.spring_with_react.model.response.create.UserGoalsResp;
import com.example.spring_with_react.repository.UserGoalsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGoalsService {

    private UserGoalsRepo userGoalsRepo;
    public UserGoalsService(@Autowired UserGoalsRepo userGoalsRepo){
        this.userGoalsRepo=userGoalsRepo;
    }

    public UserGoalsResp createuserGoals(UserGoalsReq userGoalsReq) {
        UserGoalsResp userGoalsResp =new UserGoalsResp();
        GoalEntity goalEntity = new GoalEntity();

        goalEntity.setGoalTitle(userGoalsReq.getGoalTitle());
        goalEntity.setGoalType(userGoalsReq.getGoalType());
        goalEntity.setGoalDescription(userGoalsReq.getGoalDescription());
        goalEntity.setGoalPriority(userGoalsReq.getGoalPriority());
        goalEntity.setDueDate(userGoalsReq.getDueDate());
        GoalEntity savedGoal=userGoalsRepo.save(goalEntity);

        userGoalsResp.setGoalId(savedGoal.getGoalId());
        userGoalsResp.setGoalTitle(savedGoal.getGoalTitle());
        userGoalsResp.setGoalType(savedGoal.getGoalType());
        userGoalsResp.setGoalDescription(savedGoal.getGoalDescription());
        userGoalsResp.setGoalPriority(savedGoal.getGoalPriority());
        userGoalsResp.setDueDate(savedGoal.getDueDate());
        return userGoalsResp;
    }
}
