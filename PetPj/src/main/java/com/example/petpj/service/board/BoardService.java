package com.example.petpj.service.board;

/*
* @package : com.example.demo.service
* @name : BoardService.java
* @date : 2022-08-08 오후 6:21
* @author : Rubisco
* @version : 1.0.0
* @modifyed : 
* @description : 게시판 서비스
*/

import com.example.petpj.Entity.account_info.Member;
import com.example.petpj.Entity.board.Board;
import com.example.petpj.Entity.data.FileUploadEntity;

import java.util.List;

public interface BoardService {
    List<Board> getBoardList(Board board);

    Long insertBoard(Board board);

    Board getBoard(Board board);

    void updateBoard(Board board);

    void deleteBoard(Board board);

    Board comment(Board board);

    //0822
    //작성자의 모든 게시글 출력
    List<Board> getBoardListByMemberId(Member member);

    //0901
    Long insertFileUploadEntity(FileUploadEntity fileUploadEntity);

//    FileUploadEntity getFileUploadEntity(String board_seq);

    FileUploadEntity getFileUploadEntity2(Long boardNum);
}
