package com.example.spring_with_react.model.request.create.goals;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class UserGoalsReq {
    private UUID userId;
    private String goalTitle;
    private String goalType;
    private String goalDescription;
    private String goalPriority;
    private Date dueDate;
}
