package com.example.study.domain.board.service;

import com.example.study.domain.board.dto.BoardRequestDTO;
import com.example.study.domain.board.dto.BoardResponseDTO;
import com.example.study.domain.board.entity.Board;
import com.example.study.domain.board.repository.BoardRepository;
import com.example.study.domain.user.entity.UserEntity;
import com.example.study.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createOneBoard(BoardRequestDTO dto){

        //게시글 dto -> entity
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());

        //entity 저장
        boardRepository.save(board);

        //User와 Board의 연관관계 설정
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //해당 유저의 Entity 가져오기
        UserEntity user = userRepository.findByUsername(username).orElseThrow();

        //연관관계 메소드 호출
        user.addBoard(board);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public BoardResponseDTO readOneBoard(Long id){
        Board board = boardRepository.findById(id).orElseThrow();

        BoardResponseDTO dto = new BoardResponseDTO();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());

        return dto;
    }

    //게시글 모두 읽기
    //웬만하면 지양하긴 하는 메소드임
    @Transactional(readOnly = true)
    public List<BoardResponseDTO> readAllBoards(){

        //먼저 board의 모든 내용을 가져오고
        List<Board> boards = boardRepository.findAll();

        //반환할 ResponseDTO 리스트를 만들고
        List<BoardResponseDTO> dtos = new ArrayList<>();

        //ReponseDTO 객체에 받아온 각각의 Board들에게서 필요한 정보만 빼서 dtos에 저장 후 리턴한다.
        for(Board board : boards){
            BoardResponseDTO dto = new BoardResponseDTO();
            dto.setId(board.getId());
            dto.setTitle(board.getTitle());
            dto.setContent(board.getContent());

            dtos.add(dto);
        }

        return dtos;
    }

    //게시글 하나 수정
    @Transactional
    public void updateOneBoard(Long id, BoardRequestDTO dto){

        //기존의 id에 대한 게시글 데이터 불러오기
        Board board = boardRepository.findById(id).orElseThrow();

        //게시글 dto -> entity
        //dto는 수정할 게시글 데이터임
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());

        boardRepository.save(board);

    }

    //게시글 하나 삭제
    @Transactional
    public void deleteOneBoard(Long id){
        boardRepository.deleteById(id);
    }
}
