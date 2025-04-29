package com.example.board.repository;

import com.example.board.model.Board;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
    Board findById(Long id);
    void update(Long id, Board updateBoard);
    void delete(Long id);
    void deleteAll();
}
