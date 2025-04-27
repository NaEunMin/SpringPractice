package com.example.board.controller;

import com.example.board.model.Board;
import com.example.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board") //url 경로가 board로 시작
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //가져오는 것이니 Get방식으로
    //모든 게시글을 보여주는 화면
    @GetMapping
    public String list(Model model) {

        //boardService에서 게시글 전체 리스트 가져와서 model에 담는다.
        //boards라는 키에 boardService.getAllBoard()의 리턴 값을 value로 추가
        model.addAttribute("boards", boardService.getAllBoard());
        return "board/list"; //템플릿 파일 list.html을 불러온다.
    }

    //새 게시글 저장
    @PostMapping //저장과정으로 Post

    // @ModelAttribute는 매개변수 객체를 새로 만들어서 폼에서 넘어온 값을
    // setter 함수로 알아서 넣어준다.
    public String create(@ModelAttribute Board board) {
        boardService.createBoard(board);

        return "redirect:/board";
    }

    //새 게시글 화면
    @GetMapping("/new")
    public String newForm(Model model) {

        //model 객체에 board라는 키와 new Board()를 값으로 추가
        model.addAttribute("board", new Board());
        return "board/form";
    }

    //특정 게시글 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardById(id);
        //model에 board라는 키와 boardService.getBoardById(id)를 값으로 추가
        model.addAttribute("board", board);
        return "board/detail"; //detail화면
    }

    //게시글 수정
    @GetMapping("/{id}/edit")

    // @PathVariable은 url로 넘어온 변수를 매개변수로 쓸 수 있게 해준다.
    public String editForm(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "board/form";
    }


    //게시글 수정 업데이트
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, Board board) {
        boardService.updateBoard(id, board);
        return "redirect:/board"; //수정 후 바로 목록페이지로 감
    }

    //게시글 삭제
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Board board) {
        boardService.deleteBoard(id);
        return "redirect:/board";
    }

    //전체 게시글 삭제
    @PostMapping("/deleteAll")
    public String deleteAll(){
        boardService.deleteAllBoards();
        return "redirect:/board";
    }
}
