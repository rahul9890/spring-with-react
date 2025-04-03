package com.example.spring_with_react.repository;

import com.example.spring_with_react.entities.UserDocUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocUploadRepository extends JpaRepository<UserDocUploadEntity,Integer> {
}
