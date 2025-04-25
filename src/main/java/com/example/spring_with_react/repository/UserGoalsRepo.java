package com.example.spring_with_react.repository;

import com.example.spring_with_react.entities.GoalEntity;
import com.example.spring_with_react.entities.UserGoalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserGoalsRepo extends JpaRepository<UserGoalsEntity, UUID> {

    List<UserGoalsEntity> findByUserEntity_UserId(UUID userId);
}
