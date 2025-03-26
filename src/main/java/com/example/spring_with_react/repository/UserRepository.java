package com.example.spring_with_react.repository;

import com.example.spring_with_react.utils.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{

    UserEntity save(UserEntity userEntity);

    UserEntity findUserEntityByUserEmail(String email);
}
