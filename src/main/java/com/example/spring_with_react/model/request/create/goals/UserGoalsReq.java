package com.example.spring_with_react.model.request.create.goals;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserGoalsReq {
    private String goalTitle;
    private String goalType;
    private String goalDescription;
    private String goalPriority;
    private Date dueDate;
}
