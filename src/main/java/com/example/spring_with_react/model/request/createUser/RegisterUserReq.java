package com.example.spring_with_react.model.request.createUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserReq {
    private String user_name;
    private String user_email;
    private String user_password;

}
