package com.example.study.domain.user.repository;

import com.example.study.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//<>안에는 JPA가 사용할 Entity와 그 엔티티의 기본키 타입을 작성
public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
