package com.example.board.service;

import com.example.board.model.Board;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BoardService {
    void createBoard(Board board);
    List<Board> getAllBoard();
    Board getBoardById(Long id);
    void updateBoard(Long id, Board updateBoard);
    void deleteBoard(Long id);
    void deleteAllBoards();

}
