package com.example.spring_with_react.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="user_document")
@Getter
@Setter
public class UserDocUploadEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer documentId;


    private String fileName;
    private String fileType;
    private byte[] fileData;

    @ManyToOne()
    @JoinColumn(name="user_email",referencedColumnName = "userEmail",nullable = false)
    private UserEntity userEntity;

}
