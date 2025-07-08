package com.example.board.repository;

import com.example.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
    Board findById(long id);
    void update(long id, Board updateBoard);
    void delete(long id);
    void deleteAll();
}
