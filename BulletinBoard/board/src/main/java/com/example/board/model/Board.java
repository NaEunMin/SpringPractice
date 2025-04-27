package com.example.board.model;

public class Board {
    private Long id; //게시글 구분할 ID
    private String title; //게시글 제목
    private String content; //게시글 내용


    public Board() {

    }
    //LomBok을 사용할 수도 있음.
    public Board(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
