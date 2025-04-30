package com.example.board.repository;

import com.example.board.model.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepositoryImp implements BoardRepository {

    //게시글을 저장할 Map 생성
    private final Map<Long, Board> store = new HashMap<>();

    //게시글마다 고유의 id가 있으므로 id 부여하기 위한 sequence
    private long sequence = 0L;

    //게시글 저장 함수 create 역할 수행
    public Board save(Board board) {
        board.setId(++sequence); //게시글 id 증가시키고 저장
        store.put(board.getId(), board); //Map에 게시글 저장
        return board;
    }

    //저장된 모든 게시글 조회 기능
    public List<Board> findAll(){
        return new ArrayList<>(store.values()); //모든 board 리턴
    }

    //특정 id 게시글 조회
    public Board findById(Long id) {
        return store.get(id); //키가 id인 board 리턴.
    }

    //특정 id 게시글 수정
    public void update(Long id, Board updateBoard){
        Board board = store.get(id); //특정 id의 게시글을 가져옴.
        if(board != null){
            board.setTitle(updateBoard.getTitle()); //업데이트할 board의 제목으로 바꿈
            board.setContent(updateBoard.getContent());
        }
    }

    //특정 id 게시글 삭제
    public void delete(Long id){
        store.remove(id); //Map에서 id를 가진 board 삭제
    }

    //전체 게시글 삭제
    public void deleteAll(){
        store.clear();
    }
}
