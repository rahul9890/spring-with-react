package com.example.spring_with_react.model.request.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserReq {
    private Integer userId;
    private String userName;
    private String userEmail;
}
