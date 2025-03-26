package com.example.spring_with_react.model.request.createUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserReq {
    private String name;
    private String email;
    private String password;

}
