package com.example.study.domain.user.entity;

import com.example.study.domain.board.entity.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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


    //두 객체 모두 테이블에 관여하면 혼란이 생기고 무결성을 해칠 수 있다.
    //mappedBy를 사용하여 테이블관리의 주체를 정해주는데 mappedBy가 작성되지 않은 쪽이 주체가 된다.
    //여기서는 Board 엔티티의 user가 주인다.

    //Casecade는 영속성 전이 -> 관계형 DB에서 부모-자식 관계에서 부모 엔티티가 변경되면 자식 엔티티도 함께 변경되도록 하기 위함
    //저장(persist), 병합(merge), 삭제(remove), 새로고침(refresh)기능이 있다.
    //여기서는 UserEntitiy가 부모이고 Board가 자식이다.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();


    //연관관계 편의 메소드
    //데이터베이스는 정상이지만 자바 객체들의 상태는 비정상이기때문에 연관관계 편의 메소드로 객체 상태의 일관성을 유지시켜준다.
    //user객체는 자신이 새로운 board를 가지고 있다는 사실을 전혀 모른다.
    //유저에 대해 새로운 글을 추가할 때 : 추가할 글을 받아서 연관관계에 매핑해줌
    public void addBoard(Board board){
        board.setUser(this); //board에 있는 user에 해당 유저의 정보를 저장한다.
        boards.add(board); //board 리스트에 board 추가
    }

    //유저에 대해 새로운 글을 삭제할 때 : 삭제할 글을 받아서 연관관계에서 빼준다.
    public void removeBoard(Board board){
        board.setUser(null);
        boards.remove(board);
    }
}
