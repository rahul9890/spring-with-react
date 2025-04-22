package com.example.spring_with_react.repository;

import com.example.spring_with_react.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>{

    UserEntity save(UserEntity userEntity);

    UserEntity findUserEntityByUserEmail(String email);
    UserEntity findUserEntityByUserId(UUID userId);

    @Transactional
    void deleteUserEntityByUserEmail(String email);
}
