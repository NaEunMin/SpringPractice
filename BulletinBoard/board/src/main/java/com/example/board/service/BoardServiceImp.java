package com.example.board.service;

import com.example.board.model.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//게시글 생성, 조회, 삭제
@Service
public class BoardServiceImp implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImp(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시글 생성
    public void createBoard(Board board) {
        boardRepository.save(board);
    }

    //게시글 조회
    public List<Board> getAllBoard() {
        return boardRepository.findAll();
    }

    //특정 id 게시글 조회
    public Board getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    //특정 id 게시글 수정
    public void updateBoard(Long id, Board updateBoard) {
        boardRepository.update(id, updateBoard);
    }

    //특정 id 게시글 삭제
    public void deleteBoard(Long id) {
        boardRepository.delete(id);
    }

    //전체 게시글 삭제
    public void deleteAllBoards() {
        boardRepository.deleteAll();
    }
}
