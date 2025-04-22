package com.example.spring_with_react.model.request.update;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateUserReq {
    private UUID userId;
    private String userName;
    private String userEmail;
}
