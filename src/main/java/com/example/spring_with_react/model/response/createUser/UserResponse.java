package com.example.spring_with_react.model.response.createUser;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
            private Integer userId;
        private String userName;
        private String userEmail;


}
