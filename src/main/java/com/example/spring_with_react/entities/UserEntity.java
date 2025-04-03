package com.example.spring_with_react.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userId;

    private String userName;
    private String userEmail;

    @Column(name = "user_password", columnDefinition = "BYTEA")
    @ColumnTransformer(
            read = "pgp_sym_decrypt(user_password, 'sprint_with_react_123')",
            write = "pgp_sym_encrypt(?, 'sprint_with_react_123')"
    )
    private String userPassword;


    @OneToMany(mappedBy = "userEntity", cascade=CascadeType.ALL)
    private List<UserDocUploadEntity> userDocUploadEntities;
}
