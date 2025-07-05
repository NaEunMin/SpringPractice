package com.example.study.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReq {
    private String username;
    private String password;
    private String nickname;
}
