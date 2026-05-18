package com.example.spring_with_react.service;

import com.example.spring_with_react.entities.GoalEntity;
import com.example.spring_with_react.entities.UserEntity;
import com.example.spring_with_react.entities.UserGoalsEntity;
import com.example.spring_with_react.model.request.create.goals.UserGoalsReq;
import com.example.spring_with_react.model.request.update.goals.UserGoalUpdate;
import com.example.spring_with_react.model.response.create.UserGoalsResp;
import com.example.spring_with_react.repository.UserGoalsRepo;
import com.example.spring_with_react.repository.UserRepository;
import com.example.spring_with_react.utils.DateUtil;
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

    public boolean createUserGoals(UserGoalsReq userGoalsReq) {
        UserGoalsEntity userGoalsEntity=new UserGoalsEntity();

        UserEntity userEntity=userRepository.findUserEntityByUserId(userGoalsReq.getUserId());
        if (userEntity == null) {
            throw new RuntimeException("User not found");
        }

        GoalEntity goalEntity = new GoalEntity();
        goalEntity.setGoalTitle(userGoalsReq.getGoalTitle());
        goalEntity.setGoalType(userGoalsReq.getGoalType());
        goalEntity.setGoalDescription(userGoalsReq.getGoalDescription());
        goalEntity.setGoalPriority(userGoalsReq.getGoalPriority());
        goalEntity.setGoalComments(userGoalsReq.getGoalComments());
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
            userGoalsResp.setGoalComments(userGoalsEntity.getGoalEntity().getGoalComments());
            userGoalsResp.setDueDate(DateUtil.convertTimeStampToDDMMMYYYY(userGoalsEntity.getGoalEntity().getDueDate()));
            userGoalsRespList.add(userGoalsResp);
        }
        return userGoalsRespList;
    }

    public boolean updateUserGoal(UUID goalId, UserGoalUpdate userGoalUpdate) {

        // 1️⃣ Fetch UserGoalsEntity using goalId
        UserGoalsEntity userGoalsEntity =
                userGoalsRepo.findByGoalEntity_GoalId(goalId)
                        .orElse(null);

        if (userGoalsEntity == null) {
            return false;
        }

        // 2️⃣ Get GoalEntity
        GoalEntity goalEntity = userGoalsEntity.getGoalEntity();

        // 3️⃣ Update fields
        goalEntity.setGoalTitle(userGoalUpdate.getGoalTitle());
        goalEntity.setGoalType(userGoalUpdate.getGoalType());
        goalEntity.setGoalDescription(userGoalUpdate.getGoalDescription());
        goalEntity.setGoalPriority(userGoalUpdate.getGoalPriority());
        goalEntity.setGoalComments(userGoalUpdate.getGoalComments());
        goalEntity.setDueDate(userGoalUpdate.getDueDate());

        // 4️⃣ Save
        userGoalsRepo.save(userGoalsEntity);

        return true;
    }

}
