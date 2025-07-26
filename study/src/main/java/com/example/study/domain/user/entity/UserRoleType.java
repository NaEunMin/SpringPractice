package com.example.study.domain.user.entity;

public enum UserRoleType {
    ADMIN("관리자"),
    USER("일반유저");

    //추가적인 계급의 설명
    private final String description;
    UserRoleType(String description) {
        this.description = description;
    }

}
