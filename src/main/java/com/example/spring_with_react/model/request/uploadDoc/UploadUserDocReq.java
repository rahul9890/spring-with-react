package com.example.spring_with_react.model.request.uploadDoc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class UploadUserDocReq {

    private String userEmail;
    private String fileName;
    private String fileType;
    private MultipartFile fileData;
}
