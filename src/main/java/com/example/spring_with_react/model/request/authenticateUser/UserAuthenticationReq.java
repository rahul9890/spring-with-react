package com.example.spring_with_react.model.request.authenticateUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationReq {

    private String email;
    private String password;
}
