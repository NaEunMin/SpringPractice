package com.example.study.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    //String만 들어가면 MySQL에서는 varchar256으로 들어가기 때문에 content가 길어지면 오류가 발생한다.
    //따라서 아래와 같이 작성해서 많은 내용을 작성할 수 있도록 해준다.
    @Column(columnDefinition = "TEXT")
    private String content;
}
