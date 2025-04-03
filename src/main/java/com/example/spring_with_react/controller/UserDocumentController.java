package com.example.spring_with_react.controller;

import com.example.spring_with_react.model.request.uploadDoc.UploadUserDocReq;
import com.example.spring_with_react.model.response.uploadUserData.UploadUserDocumentResp;
import com.example.spring_with_react.service.UserDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/userdocument")
public class UserDocumentController {

    private UserDocumentService userDocumentService;
    public UserDocumentController(@Autowired UserDocumentService userDocumentService){
        this.userDocumentService=userDocumentService;
    }

    @PostMapping
    public ResponseEntity<UploadUserDocumentResp> uploadUserDocument(@RequestParam("userEmail") String userEmail,
                                                                     @RequestParam("fileName") String fileName,
                                                                     @RequestParam("fileType") String fileType,
                                                                     @RequestParam("fileData") MultipartFile fileData){
    UploadUserDocReq uploadUserDocReq= UploadUserDocReq.builder().userEmail(userEmail).fileName(fileName).fileType(fileType).fileData(fileData).build();
        UploadUserDocumentResp uploadUserDocumentResp=userDocumentService.uploadUserDocument(uploadUserDocReq);
        return ResponseEntity.ok(uploadUserDocumentResp);
    }

    @GetMapping
    public ResponseEntity<List<UploadUserDocumentResp>> getAllUserDocuments(){

        List<UploadUserDocumentResp> uploadUserDocumentResps=userDocumentService.getAllUserDocs();

        return ResponseEntity.ok(uploadUserDocumentResps);
    }
}
