package com.example.study.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING) //이 어노테이션이 없다면 DB에 0번,1번 이런 식으로 저장이 된다.
    private UserRoleType role;

}
