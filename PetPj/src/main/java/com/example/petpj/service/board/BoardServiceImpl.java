package com.example.petpj.service.board;

/*
* @package : com.example.demo.service
* @name : BoardServiceImpl.java
* @date : 2022-08-08 오후 6:23
* @author : Rubisco
* @version : 1.0.0
* @modifyed : 
* @description : 게시판 서비스 구현체
*/

import com.example.petpj.Entity.account_info.Member;
import com.example.petpj.Entity.board.Board;
import com.example.petpj.Entity.data.FileUploadEntity;
import com.example.petpj.repository.board.BoardRepository;
import com.example.petpj.repository.board.FileUploadInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


//JPA가 @Service로 선언된 클래스를 갖고 JDBC에게 기능적인 구현을 위한 속성
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepo;
    //0901
    private final FileUploadInfoRepository fileUploadInfoRepo;

    //순환참조 중단
    @Autowired
    protected BoardServiceImpl(BoardRepository boardRepo,
                               FileUploadInfoRepository fileUploadInfoRepo){
        this.boardRepo = boardRepo;
        this.fileUploadInfoRepo = fileUploadInfoRepo;
    }
    //BoardRepository에 있는 DB와 연동하여 기능하는 것을 명시

    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepository의 findAll메서드를 통해서
    //전체 조회
    @Override
    public List<Board> getBoardList(Board board) {
        System.out.println("-----service getBoardList");
        return (List<Board>) boardRepo.findAll();
    }

    //클라이언트에서 받아온 Board객체의 데이터를 BoardRepository의 상속받은 CrudRepository의 Save메서드를 통해서
    //DB에 저장 (저장하는 SQL문 만들어서 실행)
    //0902 insertBoard, getBoard 수정
    @Override
    public Long insertBoard(Board board) {
        System.out.println("-----service insertBoard");
        return boardRepo.save(board).getSeq();
    }
    @Override
    public Board getBoard(Board board) {
        System.out.println("-----service getBoard");
        return boardRepo.findById(board.getSeq()).get();
    }
    @Override
    public void updateBoard(Board board) {
        System.out.println("-----service updateBoard");
        Board findBoard = boardRepo.findById(board.getSeq()).get();
        findBoard.setCategory(board.getCategory());
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        boardRepo.save(findBoard);
    }
    @Override
    public void deleteBoard(Board board) {
        System.out.println("-----service deleteBoard");
        boardRepo.deleteById(board.getSeq());
    }
    @Override
    public Board comment(Board board){
        System.out.println("-----service comment");
        return boardRepo.findById(board.getSeq()).get();
    }

    @Override
    public List<Board> getBoardListByMemberId(Member member) {
        System.out.println("-----service getBoardListByMemberId");
        //Repository
        return boardRepo.findAllByMemberIdEqualsBoardWriter(member.getId());
    }
//0901
    @Override
    public Long insertFileUploadEntity(FileUploadEntity fileUploadEntity) {
        return fileUploadInfoRepo.save(fileUploadEntity).getId();

    }

//    @Override
//    public FileUploadEntity getFileUploadEntity(String board_seq) {
//        return fileUploadInfoRepo.findByBoardSeq(Long.parseLong(board_seq));
//    }

    @Override
    public FileUploadEntity getFileUploadEntity2(Long board_Num) {
        return fileUploadInfoRepo.findByBoardNum(board_Num);
    }
}
