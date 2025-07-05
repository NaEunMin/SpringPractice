package com.example.study.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRes {
    private String username;
    private String nickname;
    private String role;
}
