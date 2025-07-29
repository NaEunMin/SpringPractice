package com.example.study.controller;

import com.example.study.domain.board.dto.BoardRequestDTO;
import com.example.study.domain.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //페이지 응답
    @GetMapping("/board/create")
    public String createPage(){
        return "createBoard";
    }

    //글 생성 : 수행
    @PostMapping("/board/create")
    public String createProcess(BoardRequestDTO boardRequestDTO){
        boardService.createOneBoard(boardRequestDTO);
        return "redirect:/board/read";
    }

    //글 목록 : 응답
    @GetMapping("/board/read")
    public String readPage(Model model){
        model.addAttribute("BOARDLIST", boardService.readAllBoards());

        return "readBoard";
    }

    //글 읽기 : 페이지 응답
    @GetMapping("/board/read/{id}")
    public String readIdPage(Model model, @PathVariable Long id){
        model.addAttribute("BOARD", boardService.readOneBoard(id));

        return "readBoard";
    }

    //글 수정 : 페이지 응답
    @GetMapping("/board/update/{id}")
    public String updatePage(@PathVariable Long id, Model model){

        //접근 권한 확인
        if(!boardService.isAccess(id)){
            return "redirect:/board/read";
        }
        model.addAttribute("BOARD", boardService.readOneBoard(id));
        return "updateBoard";
    }

    //글 수정 : 수행
    @PostMapping("/board/update/{id}")
    public String updateProcess(@PathVariable Long id, BoardRequestDTO boardRequestDTO){

        //접근 권한 확인
        if(!boardService.isAccess(id)){
            return "redirect:/board/read";
        }

        boardService.updateOneBoard(id, boardRequestDTO);
        return "redirect:/board/read" + id;
    }

    //글 삭제
    @PostMapping("/board/delete/{id}")
    public String deleteProcess(@PathVariable Long id){

        //접근 권한 확인
        if(!boardService.isAccess(id)){
            return "redirect:/board/read";
        }

        boardService.deleteOneBoard(id);

        return "redirect:/board/read";
    }


}
