package com.example.spring_with_react.model.response.uploadUserData;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadUserDocumentResp {

    private Integer documentId;
    private String userEmail;
    private String fileName;
    private String fileType;
    private String fileData;

}
