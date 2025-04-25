package com.example.spring_with_react.service;

import com.example.spring_with_react.entities.GoalEntity;
import com.example.spring_with_react.entities.UserEntity;
import com.example.spring_with_react.entities.UserGoalsEntity;
import com.example.spring_with_react.model.request.create.goals.UserGoalsReq;
import com.example.spring_with_react.model.response.create.UserGoalsResp;
import com.example.spring_with_react.repository.UserGoalsRepo;
import com.example.spring_with_react.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserGoalsService {

    private UserGoalsRepo userGoalsRepo;
    private UserRepository userRepository;
    public UserGoalsService(@Autowired UserGoalsRepo userGoalsRepo,@Autowired UserRepository userRepository){
        this.userGoalsRepo=userGoalsRepo;
        this.userRepository=userRepository;
    }

    public boolean createuserGoals(UserGoalsReq userGoalsReq) {
        UserGoalsResp userGoalsResp =new UserGoalsResp();
        UserGoalsEntity userGoalsEntity=new UserGoalsEntity();

        UserEntity userEntity=userRepository.findUserEntityByUserId(userGoalsReq.getUserId());

        GoalEntity goalEntity = new GoalEntity();
        goalEntity.setGoalTitle(userGoalsReq.getGoalTitle());
        goalEntity.setGoalType(userGoalsReq.getGoalType());
        goalEntity.setGoalDescription(userGoalsReq.getGoalDescription());
        goalEntity.setGoalPriority(userGoalsReq.getGoalPriority());
        goalEntity.setDueDate(userGoalsReq.getDueDate());

        userGoalsEntity.setUserEntity(userEntity);
        userGoalsEntity.setGoalEntity(goalEntity);
        UserGoalsEntity savedUserGoalEntity=userGoalsRepo.save(userGoalsEntity);

        if(null!=savedUserGoalEntity){
            return true;
        }

        return false;
    }

    public List<UserGoalsResp> getGoalsForUser(UUID userId) {
        List<UserGoalsResp> userGoalsRespList=new ArrayList<>();
        List<UserGoalsEntity> userGoalsEntities=userGoalsRepo.findByUserEntity_UserId(userId);
        for (UserGoalsEntity userGoalsEntity:userGoalsEntities){
            UserGoalsResp userGoalsResp=new UserGoalsResp();
            userGoalsResp.setGoalId(userGoalsEntity.getGoalEntity().getGoalId());
            userGoalsResp.setGoalTitle(userGoalsEntity.getGoalEntity().getGoalTitle());
            userGoalsResp.setGoalType(userGoalsEntity.getGoalEntity().getGoalType());
            userGoalsResp.setGoalDescription(userGoalsEntity.getGoalEntity().getGoalDescription());
            userGoalsResp.setGoalPriority(userGoalsEntity.getGoalEntity().getGoalPriority());
            userGoalsResp.setDueDate(userGoalsEntity.getGoalEntity().getDueDate());
            userGoalsRespList.add(userGoalsResp);
        }
        return userGoalsRespList;
    }
}
