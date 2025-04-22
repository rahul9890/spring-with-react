package com.example.spring_with_react.model.response.createUser;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID userId;
        private String userName;
        private String userEmail;


}
