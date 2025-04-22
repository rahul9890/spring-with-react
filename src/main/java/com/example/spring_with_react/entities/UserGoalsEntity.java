package com.example.spring_with_react.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "user_goals")
@Getter
@Setter
public class UserGoalsEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userGoalsId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)   //this will create goal entity at runtime for userEntity
    @JoinColumn(name = "goal_id")
    private GoalEntity goalEntity;
}
