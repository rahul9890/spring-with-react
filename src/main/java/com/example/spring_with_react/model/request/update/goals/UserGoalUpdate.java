package com.example.spring_with_react.model.request.update.goals;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class UserGoalUpdate {
    private UUID goalId;
    private String goalTitle;
    private String goalType;
    private String goalDescription;
    private String goalPriority;
    private String goalComments;
    private String dueDate;
}
