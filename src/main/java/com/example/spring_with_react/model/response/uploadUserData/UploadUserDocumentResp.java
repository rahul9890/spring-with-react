package com.example.spring_with_react.model.response.uploadUserData;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadUserDocumentResp {

    private Integer documentId;
    private String userEmail;
    private String fileName;
    private String fileType;
    private MultipartFile fileData;

}
