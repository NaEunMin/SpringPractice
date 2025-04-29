package com.example.board.testRepository;

import com.example.board.model.Board;
import com.example.board.repository.BoardRepositoryImp;
import org.junit.jupiter.api.Test;

public class TestBoardRepositoryImp {

    BoardRepositoryImp repository = new BoardRepositoryImp();

    public void save(){
        long seqeunce = 0L;
        Board board = new Board();
        board.setId(++seqeunce);
        //board.setTitle();
    }


}
