package com.example.spring_with_react.repository;

import com.example.spring_with_react.entities.GoalEntity;
import com.example.spring_with_react.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserGoalsRepo extends JpaRepository<GoalEntity, UUID> {
}
