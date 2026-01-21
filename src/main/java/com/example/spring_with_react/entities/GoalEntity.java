package com.example.spring_with_react.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "goals")
@Getter
@Setter
public class GoalEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "goal_id", nullable = false)
    private UUID goalId;

    @Column(name = "goal_title", nullable = false, length = 100)
    private String goalTitle;

    @Column(name = "goal_type", length = 10)
    private String goalType;

    @Column(name = "goal_description", length = 200)
    private String goalDescription;

    @Column(name = "goal_priority", length = 10)
    private String goalPriority;

    @Column(name = "goal_comments" ,length = 100)
    private String goalComments;

    @Column(name = "due_date")
    private Date dueDate;
}
