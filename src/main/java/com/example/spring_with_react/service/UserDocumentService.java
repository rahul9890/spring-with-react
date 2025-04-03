package com.example.spring_with_react.service;

import com.example.spring_with_react.entities.UserDocUploadEntity;
import com.example.spring_with_react.entities.UserEntity;
import com.example.spring_with_react.model.request.uploadDoc.UploadUserDocReq;
import com.example.spring_with_react.model.response.uploadUserData.UploadUserDocumentResp;
import com.example.spring_with_react.repository.UserDocUploadRepository;
import com.example.spring_with_react.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserDocumentService {

    private UserDocUploadRepository userDocUploadRepository;
    private UserRepository userRepository;
    public UserDocumentService(@Autowired UserDocUploadRepository userDocUploadRepository,
    @Autowired UserRepository userRepository) {

        this.userDocUploadRepository = userDocUploadRepository;
        this.userRepository=userRepository;
    }

    public UploadUserDocumentResp uploadUserDocument(UploadUserDocReq uploadUserDocReq) {

        UserEntity userEntity=userRepository.findUserEntityByUserEmail(uploadUserDocReq.getUserEmail());


        UserDocUploadEntity userDocUploadEntity=new UserDocUploadEntity();
        userDocUploadEntity.setUserEntity(userEntity);
        userDocUploadEntity.setFileName(uploadUserDocReq.getFileName());
        userDocUploadEntity.setFileType(uploadUserDocReq.getFileType());
        try {
            userDocUploadEntity.setFileData(uploadUserDocReq.getFileData().getBytes());
        }catch (IOException e){
            throw  new RuntimeException(e);
        }


        UserDocUploadEntity savedUserDocUploadEntity=   userDocUploadRepository.save(userDocUploadEntity);

        UploadUserDocumentResp uploadUserDocumentResp=new UploadUserDocumentResp();
        uploadUserDocumentResp.setDocumentId(savedUserDocUploadEntity.getDocumentId());
        uploadUserDocumentResp.setUserEmail(savedUserDocUploadEntity.getUserEntity().getUserEmail());

        uploadUserDocumentResp.setFileName(savedUserDocUploadEntity.getFileName());
        uploadUserDocumentResp.setFileType(savedUserDocUploadEntity.getFileType());



        return uploadUserDocumentResp;
    }
}
